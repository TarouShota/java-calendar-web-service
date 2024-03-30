package webCalendarSpring.demo;

import jakarta.persistence.*;

import java.time.LocalDate;


//Create an webCalendarSpring.Event entity to save events to the database. The table should contain the following columns:
//
//id of the INTEGER type. It should be our PRIMARY KEY. Its value will be incremented and generated automatically. Starting from 1.
//event of the VARCHAR type. It should be NOT NULL.
//date of the DATE type. It should be NOT NULL.`

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
