package com.example.population.repository;

import com.example.population.models.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
}

// hace el update aparte del .save