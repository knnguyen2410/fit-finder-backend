package com.example.fitfinder.controller;

import com.example.fitfinder.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/gyms")
public class GymController {

    @Autowired
    private GymService gymService;
}
