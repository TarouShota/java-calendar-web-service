package webCalendarSpring.demo;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String event;
    @Column(nullable = false)
    private LocalDate date;

    public Event(String event, LocalDate date) {
        this.event = event;
        this.date = date;
    }

    public Event(String event, String date) {
        this.event = event;
        this.date = LocalDate.parse(date);
    }

    public Event() {
    }

    public String getEvent() {
        return event;
    }

    public LocalDate getDate() {
        return date;
    }
    public String getDateString(){
        return getDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public long getId() {
        return id;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
