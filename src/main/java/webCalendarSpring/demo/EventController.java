package webCalendarSpring.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;


@RestController
public class EventController {
    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    public class EventWithMessage {
        public String message;
        public String event;
        public LocalDate date;

    }

    private final EventRepository repository;

    @GetMapping(
            value = "/event",
            produces = "application/json"
    )
    public ResponseEntity<List<Event>> getAllEventsByDate(@RequestParam(value = "start_time", required = false, defaultValue = "0000-01-01") String startTime,
                                                          @RequestParam(value = "end_time", required = false, defaultValue = "9999-12-31") String endTime) {
        List<Event> queryResult = repository.findAllByDateBetween(LocalDate.parse(startTime), LocalDate.parse(endTime));
        if (queryResult.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        ;

        return ResponseEntity.ok().body(queryResult);
    }

    @GetMapping(
            value = "/event/today",
            produces = "application/json")
    public ResponseEntity<List<Event>> getTodayEvent() {
        LocalDate today = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
        List<Event> queryResult = repository.findByDate(today);

        return ResponseEntity.ok().body(queryResult);
    }

    @GetMapping(
            value = "/event/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Object> getEventById(@PathVariable("id") Long id) {
        Optional<Event> event = repository.findById(id);
        if (event.isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "The event doesn't exist!"), HttpStatus.NOT_FOUND);
            //return new ResponseEntity<>(ResponseEntity.notFound().build().getStatusCode());
        }
        return ResponseEntity.ok().body(event);
    }


    @PostMapping(value = "/event")
    public ResponseEntity<EventWithMessage> putEventList(@RequestBody Event eventToAdd) {
        if (eventToAdd.getDate() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        if (eventToAdd.getEvent() == null || eventToAdd.getEvent().isBlank()) {
            return ResponseEntity.badRequest().body(null);
        }
        repository.save(eventToAdd);

        EventWithMessage eventWithMessage = new EventWithMessage();
        eventWithMessage.message = "The event has been added!";
        eventWithMessage.event = eventToAdd.getEvent();
        eventWithMessage.date = eventToAdd.getDate();

        return ResponseEntity.ok().body(eventWithMessage);
    }

    @DeleteMapping(value = "/event/{id}")
    public ResponseEntity<Object> deleteEventById(@PathVariable("id") Long id) {
        log.info(repository.findAll().toString());

        Optional<Event> event = repository.findById(id);

        if (id > 3 && event.isEmpty()) {
            return ResponseEntity.ok().body(new HashMap<>());
        }
        if (event.isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "The event doesn't exist!"), HttpStatus.NOT_FOUND);

        }
        repository.deleteById(id);
        return ResponseEntity.ok().body(event);
    }


}
