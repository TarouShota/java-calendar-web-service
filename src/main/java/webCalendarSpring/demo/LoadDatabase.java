package webCalendarSpring.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
    @Bean
    public CommandLineRunner initDatabase(EventRepository repository) {

        return (args) -> {
        };
    }

}


