package webCalendarSpring.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="The event doesn't exist!")  // 404
public class EventNotFoundException extends RuntimeException {
}