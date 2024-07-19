package com.example.population;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.population"})
public class PopulationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PopulationApiApplication.class, args);
    }

}