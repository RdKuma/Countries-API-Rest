package com.example.population.service;

import com.example.population.models.CountryEntity;
import com.example.population.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CountryServiceTest {
    @Autowired
    private CountryService countryService;

    @MockBean
    private CountryRepository countryRepository;

    @Test
    public void testGetAllCountries() {
        CountryEntity country1 = new CountryEntity();
        country1.setName("South Georgia");
        country1.setPopulation(30); // Ejemplo de población

        CountryEntity country2 = new CountryEntity();
        country2.setName("France");
        country2.setPopulation(67000000);

        List<CountryEntity> mockCountries = Arrays.asList(country1, country2);
        when(countryRepository.findAll()).thenReturn(mockCountries);

        List<CountryEntity> result = countryService.getAllCountries();

        assertEquals(2, result.size());
        assertEquals("South Georgia", result.get(0).getName());
        assertEquals("France", result.get(1).getName());
    }
}