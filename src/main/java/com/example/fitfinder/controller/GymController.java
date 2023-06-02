package com.example.fitfinder.controller;

import com.example.fitfinder.models.Gym;
import com.example.fitfinder.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/gyms")
public class GymController {

    @Autowired
    private GymService gymService;

    @GetMapping(path = "")
    public List<Gym> getAllGyms(){
        return gymService.getAllGyms();
    }
}
