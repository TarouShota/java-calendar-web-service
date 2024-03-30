package webCalendarSpring.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import webCalendarSpring.demo.Event;

//@RepositoryRestResource(collectionResourceRel = "event", path = "event")
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDate(LocalDate date);

    Event findById(long id);
}