package webCalendarSpring.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;


@RestController("/")
public class EventController {
    private final List<String> todayEvent = Collections.emptyList();
    public static class EventClass {
        public String event;
        public LocalDate date;
    }
    public static class EventClassResponse extends EventClass{
        public String message;

    }

//    @GetMapping(
//            value = "event/today",
//            produces="application/json")
//
//    public ResponseEntity<List<String>> getTodayEventList(){
//
//        return ResponseEntity.ok().body(todayEvent);
//
//    }
//    @PostMapping(value = "event")
//    public  ResponseEntity<EventClassResponse> putEventList(@RequestBody EventClass requestEvent){
//        if(requestEvent.date==null) {
//            return ResponseEntity.badRequest().body(null);
//        }
//        if(requestEvent.event==null || requestEvent.event.isBlank()){
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        EventClassResponse response = new EventClassResponse();
//        response.message = "The event has been added!";
//        response.event = requestEvent.event;
//        response.date=requestEvent.date;
//
//
//        return ResponseEntity.ok().body(response);
//    }

}
