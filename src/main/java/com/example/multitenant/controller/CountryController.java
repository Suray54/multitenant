package com.example.multitenant.controller;

import com.example.multitenant.model.Country;
import com.example.multitenant.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    // Get all countries
    @GetMapping
    public List<Country> getCountries() {
        return countryService.getAllCountries();
    }

    // Get country by id
    @GetMapping("/{id}")
    public Optional<Country> getCountryById(@PathVariable Long id) {
        return countryService.getCountryById(id);
    }

    // Add a new country
    @PostMapping
    public Country addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    // Update a country
    @PutMapping("/{id}")
    public Country updateCountry(@PathVariable Long id, @RequestBody Country country) {
        country.setId(id);
        return countryService.updateCountry(country);
    }

    // Delete a country
    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
    }
}
