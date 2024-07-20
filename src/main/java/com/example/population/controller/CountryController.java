package com.example.population.controller;

import com.example.population.models.Country;
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

    @PostMapping("/country")
    public ResponseEntity<List<Country>> fetchAndSaveCountries() {
        List<Country> savedCountries = countryService.fetchAndSaveCountries();
        return ResponseEntity.ok(savedCountries);
    }

    @GetMapping("/country")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/")
    public String home() {
        return "Bienvenido a la API de datos poblacionales";
    }
}