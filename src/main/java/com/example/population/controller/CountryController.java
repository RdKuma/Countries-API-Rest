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
    public ResponseEntity<Country> saveOrUpdateCountry(@RequestBody Country country) {
        Country savedCountry = countryService.saveOrUpdateCountry(country);
        return ResponseEntity.ok(savedCountry);
    }

    @GetMapping("/country")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }
}