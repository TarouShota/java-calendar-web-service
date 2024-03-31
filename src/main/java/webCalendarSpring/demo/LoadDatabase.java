package webCalendarSpring.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


