package com.example.population.service;

import com.example.population.models.Country;
import com.example.population.models.CountryEntity;
import com.example.population.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    private static final Logger logger = Logger.getLogger(CountryService.class.getName());

    public List<CountryEntity> fetchAndSaveCountries() {
        String url = "https://restcountries.com/v3.1/all";
        RestTemplate restTemplate = new RestTemplate();
        Country[] countries = restTemplate.getForObject(url, Country[].class);

        if (countries != null) {
            Arrays.stream(countries).forEach(country -> {
                CountryEntity newCountry = new CountryEntity();
                newCountry.setName(country.getName().getCommon());
                newCountry.setPopulation(country.getPopulation());

                logger.info("Saving country: " + newCountry.getName() + " with population: " + newCountry.getPopulation());
                countryRepository.save(newCountry);
            });
        } else {
            logger.warning("No countries found from external API.");
        }

        return countryRepository.findAll();
    }

    public List<CountryEntity> getAllCountries() {
        return countryRepository.findAll();
    }
}
