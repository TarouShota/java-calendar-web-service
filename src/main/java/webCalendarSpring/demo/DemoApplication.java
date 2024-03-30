package webCalendarSpring.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(EventRepository repository) {
		LocalDate sample = LocalDate.of(2024,03,30);

		return (args)->{
			repository.save(new Event("shibal-event","2024-06-01"));
			repository.save(new Event("sample-event",sample));


			log.info("get event");
			log.info(repository.findById(1).getEvent());
			log.info(repository.findByDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth())).get(0).getEvent());
		};
	}
}
