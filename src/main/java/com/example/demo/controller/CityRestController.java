package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.service.city.CityService;
import com.example.demo.service.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/city")
public class CityRestController {
    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @GetMapping("")
    public ResponseEntity<Iterable<City>> showList(){
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<City>> findCityById(@PathVariable Long id){
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cityOptional, HttpStatus.OK);
    }

    @GetMapping("/country")
    public ResponseEntity<Iterable<Country>> listCountry(){
        return  new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<City> saveCity(@RequestBody City city){
        return new ResponseEntity<>(cityService.save(city), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<City> delete(@PathVariable Long id){
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityService.deleteById(cityOptional.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
