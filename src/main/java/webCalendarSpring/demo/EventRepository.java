package webCalendarSpring.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource(collectionResourceRel = "event", path = "event")
interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDate(LocalDate date);

    Event findById(long id);

    void deleteById(long id);

    void deleteAllById(long id);

    List<Event> findAllByDateBetween(LocalDate startTime, LocalDate endTime);
}