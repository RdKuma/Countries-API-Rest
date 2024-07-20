package com.example.population.controller;

import com.example.population.models.CountryEntity;
import com.example.population.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/data")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/country/load")
    public ResponseEntity<List<CountryEntity>> fetchAndSaveCountries() {
        List<CountryEntity> savedCountries = countryService.fetchAndSaveCountries();
        return ResponseEntity.ok(savedCountries);
    }

    @GetMapping("/country/list")
    public ResponseEntity<List<CountryEntity>> getAllCountries() {
        List<CountryEntity> countries = countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to this API Rest";
    }
}
