package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.service.city.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("")
    public ModelAndView showCity() {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("city", cityService.findAll());
        return modelAndView;
    }
}
