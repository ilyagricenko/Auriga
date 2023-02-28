package com.taskmanager.auriga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.taskmanager")
@ComponentScan("com.taskmanager")
@EnableJpaRepositories("com.taskmanager")
public class AurigaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AurigaApplication.class, args);
    }

}
