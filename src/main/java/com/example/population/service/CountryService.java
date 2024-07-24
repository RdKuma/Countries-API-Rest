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

    public void fetchAndSaveCountries() {
        String url = "https://restcountries.com/v3.1/all";
        RestTemplate restTemplate = new RestTemplate();
        Country[] countries = restTemplate.getForObject(url, Country[].class);

        if (countries != null) {
            Arrays.stream(countries).forEach(country -> {
                String countryName = country.getName().getCommon();
                CountryEntity existingCountry = countryRepository.findByCountry(countryName);

                if (existingCountry != null) {
                    existingCountry.setPopulation(country.getPopulation());
                    countryRepository.save(existingCountry);
                    logger.info("Updated country: " + countryName + " with population: " + country.getPopulation());
                } else {
                    CountryEntity newCountry = new CountryEntity();
                    newCountry.setCountry(countryName);
                    newCountry.setPopulation(country.getPopulation());
                    countryRepository.save(newCountry);
                    logger.info("Saved new country: " + countryName + " with population: " + country.getPopulation());
                }
            });
        } else {
            logger.warning("No countries found from external API.");
        }
    }

    public List<CountryEntity> getAllCountries() {
        return countryRepository.findAll();
    }
}