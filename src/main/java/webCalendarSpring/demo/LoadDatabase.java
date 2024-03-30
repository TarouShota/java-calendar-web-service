package webCalendarSpring.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
@Configuration
class LoadDatabase {
    LocalDate sample = LocalDate.of(2024,03,30);
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    public CommandLineRunner initDatabase(EventRepository repository) {

        return (args)->{
            repository.save(new Event("shibal-event","2024-06-01"));
            repository.save(new Event("sample-event",sample));

        log.info("NEAL " +  repository.save(new Event("sample-event_v2",sample)));
            log.info("get event");
            log.info(repository.findById(1).getEvent());
            log.info(repository.findByDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth())).get(0).getEvent());
        };
    }

}


