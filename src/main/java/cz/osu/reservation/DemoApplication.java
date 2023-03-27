package cz.osu.reservation;

import cz.osu.reservation.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(PersonRepository repository) {
        return args -> {
            //   repository.save(new PersonEntity("Petr", "Petr XXXX", "77888",
            // 205,"lallala@seznam.cz"));
            // repository.save(new Person2("Helena", "Helena AAAAA", 25));
            //repository.save(new Person2("Tomáš", "Tomáš ZZZZ", 27));
        };
    }

}
