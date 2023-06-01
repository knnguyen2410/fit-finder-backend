package com.example.fitfinder.controller;

import com.example.fitfinder.service.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/gyms/{gymId}/amenity")
public class AmenityController {

    @Autowired
    private AmenityService amenityService;
}
