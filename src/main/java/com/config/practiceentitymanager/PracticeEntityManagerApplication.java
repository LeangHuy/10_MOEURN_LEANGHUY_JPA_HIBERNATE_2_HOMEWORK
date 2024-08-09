package com.config.practiceentitymanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Homework-entity-manager",
        version = "v1",
        description = "Homework EntityManager"))
public class PracticeEntityManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeEntityManagerApplication.class, args);
    }

}
