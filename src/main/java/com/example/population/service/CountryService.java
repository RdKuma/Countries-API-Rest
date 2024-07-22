package com.example.population.service;

import com.example.population.models.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String COUNTRIES_API_URL = "https://restcountries.com/v3.1/all";

    public List<Country> getAllCountries() {
        ResponseEntity<List<ApiCountry>> response = restTemplate.exchange(
                COUNTRIES_API_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ApiCountry>>() {}
        );

        List<ApiCountry> apiCountries = response.getBody();

        return apiCountries.stream()
                .map(apiCountry -> new Country(apiCountry.getName().getCommon(), apiCountry.getPopulation()))
                .collect(Collectors.toList());
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ApiCountry {
        private Name name;
        private long population;

        public Name getName() { return name; }
        public void setName(Name name) { this.name = name; }
        public long getPopulation() { return population; }
        public void setPopulation(long population) { this.population = population; }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Name {
            private String common;

            public String getCommon() { return common; }
            public void setCommon(String common) { this.common = common; }
        }
    }
}