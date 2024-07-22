package com.example.population.service;

import com.example.population.models.CountryEntity;
import com.example.population.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<CountryEntity> saveCountries(List<CountryEntity> countries) {
        List <CountryEntity> savedCountries = countries.stream()
                .map(this::saveCountryIfNotExists)
                .collect(Collectors.toList());
        return savedCountries;
    }

    private CountryEntity saveCountryIfNotExists(CountryEntity country) {
        CountryEntity existingCountry = countryRepository.findByName(country.getName());
        if (existingCountry == null) {
            return countryRepository.save(country);
        } else {
            // Si deseas actualizar la información del país existente, descomenta la siguiente línea:
            // existingCountry.setPopulation(country.getPopulation());
            // return countryRepository.save(existingCountry);
            return existingCountry;
        }
    }

    public List<CountryEntity> getAllCountries() {
        return countryRepository.findAll();
    }

    // El método fetchAndSaveCountries puede ser removido si no es necesario
    public List<CountryEntity> fetchAndSaveCountries() {
        // Implementa la lógica si es necesario
        return List.of(); // Devuelve una lista vacía o implementa la lógica correspondiente
    }
}
