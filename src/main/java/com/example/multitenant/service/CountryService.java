package com.example.multitenant.service;

import com.example.multitenant.model.Country;
import com.example.multitenant.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    // Get all countries
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // Get a country by id
    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id);
    }

    // Add a new country
    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    // Update a country
    public Country updateCountry(Country country) {
        return countryRepository.save(country);
    }

    // Delete a country
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
