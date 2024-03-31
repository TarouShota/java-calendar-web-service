package webCalendarSpring.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class EventController {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    static final String minDate = "0000-01-01", maxDate = "9999-12-31";
    private final EventRepository repository;
    private final EventModelAssembler assembler;

    public EventController(EventRepository repository, EventModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping(value = "/event", produces = "application/json")
    public CollectionModel<EntityModel<Event>> getAllEvents(@RequestParam(value = "start_time", required = false, defaultValue = minDate) String startTime,
                                                            @RequestParam(value = "end_time", required = false, defaultValue = maxDate) String endTime) {


        List<EntityModel<Event>> queryResult = repository.findAllByDateBetween(LocalDate.parse(startTime), LocalDate.parse(endTime))
                .stream()
                .map(assembler::toModel).collect(Collectors.toList());

        if (queryResult.isEmpty()) {
            throw new EventNotFoundException();
        }
        return CollectionModel.of(queryResult, linkTo(methodOn(EventController.class).getAllEvents(startTime, endTime)).withSelfRel());
    }

    @GetMapping(value = "/event/today", produces = "application/json")
    public CollectionModel<EntityModel<Event>> getTodayEvent() {
        LocalDate today = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
        List<EntityModel<Event>> queryResult = repository.findByDate(today).stream().map(assembler::toModel).collect(Collectors.toList());

        if (queryResult.isEmpty()) {
            throw new EventNotFoundException();
        }
        return CollectionModel.of(queryResult, linkTo(methodOn(EventController.class).getTodayEvent()).withSelfRel());
    }

    @GetMapping(value = "/event/{id}", produces = "application/json")
    public EntityModel<Event> getEventById(@PathVariable("id") Long id) {
        Event event = repository.findById(id).orElseThrow(EventNotFoundException::new);

        return assembler.toModel(event);
    }


    @PostMapping(value = "/event")
    public ResponseEntity<?> newEvent(@RequestBody Event eventToAdd) {
        if (eventToAdd.getDate() == null || eventToAdd.getEvent() == null || eventToAdd.getEvent().isBlank()) {
            throw new InvalidEventException();
        }

        EntityModel<Event> entityModel = assembler.toModel(repository.save(eventToAdd));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

    }

    @DeleteMapping(value = "/event/{id}")
    public EntityModel<Event> deleteEventById(@PathVariable("id") Long id) {
        Event event = repository.findById(id).orElseThrow(EventNotFoundException::new);
        repository.deleteById(id);
        return assembler.toModel(event);
    }
}
