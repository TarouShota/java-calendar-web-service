package webCalendarSpring.demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static webCalendarSpring.demo.EventController.maxDate;
import static webCalendarSpring.demo.EventController.minDate;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EventModelAssembler implements RepresentationModelAssembler<Event, EntityModel<Event>> {
    @Override
    public EntityModel<Event> toModel(Event event) {

        return EntityModel.of(event,
                linkTo(methodOn(EventController.class).getEventById(event.getId())).withSelfRel(),
                linkTo(methodOn(EventController.class).getAllEvents(event.getDateString(), event.getDateString())).withRel("event"));
    }
}
