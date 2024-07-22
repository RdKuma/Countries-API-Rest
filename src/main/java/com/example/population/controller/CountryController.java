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
        return "Bienvenido a la API de datos poblacionales";
    }

    // Endpoint para cargar los países
    @PostMapping("/api/v1/data/country")
    public ResponseEntity<List<CountryEntity>> fetchAndSaveCountries(@RequestBody List<CountryEntity> countries) {
        List<CountryEntity> savedCountries = countryService.saveCountries(countries);
        return ResponseEntity.ok(savedCountries);
    }

    // Endpoint para listar todos los países
    @GetMapping("/api/v1/data/country")
    public ResponseEntity<List<CountryEntity>> getAllCountries() {
        List<CountryEntity> countries = countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }
}
