package com.example.population.service;

import com.example.population.models.Country;
import com.example.population.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String API_URL = "https://restcountries.com/v3.1/all?fields=name,population";

    public List<Country> fetchAndSaveCountries() {
        ResponseEntity<List<ApiCountry>> responseEntity = restTemplate.exchange(
            API_URL,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<ApiCountry>>() {}
        );

        List<ApiCountry> apiCountries = responseEntity.getBody();
        
        List<Country> countries = apiCountries.stream()
            .map(apiCountry -> new Country(apiCountry.getName().getCommon(), apiCountry.getPopulation()))
            .collect(Collectors.toList());

        return countryRepository.saveAll(countries);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // Clase interna para mapear la respuesta de la API
    private static class ApiCountry {
        private Name name;
        private Long population;

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Long getPopulation() {
            return population;
        }

        public void setPopulation(Long population) {
            this.population = population;
        }

        public static class Name {
            private String common;

            public String getCommon() {
                return common;
            }

            public void setCommon(String common) {
                this.common = common;
            }
        }
    }
}