package webCalendarSpring.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Invalid event format. Date should be in 'yyyy-mm-dd' format and event should not be blank.")  // 404
public class InvalidEventException extends RuntimeException {
}