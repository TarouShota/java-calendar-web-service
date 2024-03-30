package webCalendarSpring.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;


@RestController
public class EventController {
//    public class EventWithMessage extends Event{
//        public EventWithMessage(Event event){
//
//        }
//    }
    private final EventRepository repository;
    public EventController(EventRepository repository) {
        this.repository = repository;
    }
    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping(
            value = "/event",
            produces="application/json")
    public ResponseEntity<List<Event>> getAllEvents(){
        List<Event> queryResult = repository.findAll();
        if(queryResult.isEmpty()){
            return ResponseEntity.notFound().build();
        };

        return ResponseEntity.ok().body(queryResult);
    }
    @GetMapping(
            value = "/event/today",
            produces="application/json")
    public ResponseEntity<List<Event>> getTodayEvent(){
        LocalDate today = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth());
        List<Event> queryResult = repository.findByDate(today);

        return ResponseEntity.ok().body(queryResult);
    }


    @PostMapping(value = "/event")
    public  ResponseEntity<Event> putEventList(@RequestBody Event eventToAdd ){
        if(eventToAdd.getDate()==null) {
            return ResponseEntity.badRequest().body(null);
        }
        if(eventToAdd.getEvent()==null || eventToAdd.getEvent().isBlank()){
            return ResponseEntity.badRequest().body(null);
        }
        repository.save(eventToAdd);
        return ResponseEntity.ok().body(null);
    }

}
