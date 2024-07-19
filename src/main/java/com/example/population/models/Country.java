package com.example.population.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Country {
    @Id
    private String name;
    private Long population;

    // Constructor por defecto
    public Country() {
    }

    // Constructor con parámetros
    public Country(String name, Long population) {
        this.name = name;
        this.population = population;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Long getPopulation() {
        return population;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}