package com.example.population.controller;

import com.example.population.models.CountryEntity;
import com.example.population.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    // Endpoint de bienvenida
    @GetMapping("/")
    public String home() {
        return "Welcome to the Country API";
    }

    // Endpoint para cargar los países
    @PostMapping("/api/v1/data/country")
    public ResponseEntity<String> fetchAndSaveCountries() {
        countryService.fetchAndSaveCountries();
        return ResponseEntity.ok("Todos los datos han sido cargados.");
    }

    // Endpoint para listar todos los países
    @GetMapping("/api/v1/data/country")
    public ResponseEntity<List<CountryEntity>> getAllCountries() {
        List<CountryEntity> countries = countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }
}
