package com.example.population.repository;

import com.example.population.models.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
    CountryEntity findByName(String name);
}
