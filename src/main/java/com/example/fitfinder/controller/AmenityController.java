package com.example.fitfinder.controller;

import com.example.fitfinder.models.Amenity;
import com.example.fitfinder.models.Equipment;
import com.example.fitfinder.service.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/gyms/{gymId}/amenities")
public class AmenityController {

    @Autowired
    private AmenityService amenityService;

    @GetMapping(path = "")
    public List<Amenity> getAllAmenitiesByGymId(@PathVariable Long gymId) {
        return amenityService.getAllAmenitiesByGymId(gymId);
    }
}
