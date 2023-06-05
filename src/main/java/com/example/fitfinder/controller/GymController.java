package com.example.fitfinder.controller;

import com.example.fitfinder.models.Gym;
import com.example.fitfinder.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/gyms")
public class GymController {

    @Autowired
    private GymService gymService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public Gym createGym(@RequestBody Gym gymObject){
        return gymService.createGym(gymObject);
    }

    @GetMapping(path = "")
    public List<Gym> getAllGyms(){
        return gymService.getAllGyms();
    }

    @GetMapping(path = "/{gymId}")
    public Gym getGymById(@PathVariable Long gymId){
        return gymService.getGymById(gymId);
    }
}
