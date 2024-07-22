package com.example.population.controller;

import com.example.population.models.Country;
import com.example.population.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/")
    public String home() {
        return "Bienvenido a mi API Rest. Visita /api/v1/data/country para ver los datos de los países.";
    }

    @GetMapping("/api/v1/data/country")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }
}