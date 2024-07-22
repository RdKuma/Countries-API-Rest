package com.example.population.repository;

import com.example.population.models.CountryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void testSaveAndRetrieveCountry() {
        CountryEntity country = new CountryEntity();
        country.setName("TestCountry");
        country.setPopulation(1000000);

        CountryEntity savedCountry = countryRepository.save(country);
        CountryEntity retrievedCountry = countryRepository.findById(savedCountry.getId()).orElse(null);

        assertNotNull(retrievedCountry);
        assertEquals("TestCountry", retrievedCountry.getName());
    }
}