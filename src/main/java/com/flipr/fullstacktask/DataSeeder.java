package com.flipr.fullstacktask;

import com.flipr.fullstacktask.model.*;
import com.flipr.fullstacktask.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner run(
            ProjectRepository projectRepo,
            ClientRepository clientRepo,
            ContactFormRepository contactRepo,
            SubscriberRepository subscriberRepo
    ) {
        return args -> {
            // Clear old data (optional)
            projectRepo.deleteAll();
            clientRepo.deleteAll();
            contactRepo.deleteAll();
            subscriberRepo.deleteAll();

            // Insert Projects
            projectRepo.saveAll(List.of(
                    Project.builder()
                            .name("Portfolio Website")
                            .description("A responsive portfolio using HTML/CSS/JS")
                            .imageUrl("https://dummyimage.com/300x200/111/fff")
                            .build(),

                    Project.builder()
                            .name("Flipr Task Backend")
                            .description("Spring Boot REST APIs for Flipr task")
                            .imageUrl("https://dummyimage.com/300x200/222/fff")
                            .build()
            ));

            // Insert Clients
            clientRepo.saveAll(List.of(
                    Client.builder().name("Google").logoUrl("https://logo.clearbit.com/google.com").build(),
                    Client.builder().name("Amazon").logoUrl("https://logo.clearbit.com/amazon.com").build()
            ));

            // Insert ContactForm messages
            contactRepo.save(ContactForm.builder()
                    .name("Vikas")
                    .email("vikas@example.com")
                    .mobile("9876543210")
                    .city("Delhi")
                    .message("I love this site!")
                    .build()
            );

            // Insert Subscribers
            subscriberRepo.save(Subscriber.builder()
                    .email("subscriber@example.com")
                    .build()
            );

            System.out.println("✅ Sample data inserted!");
        };
    }
}
