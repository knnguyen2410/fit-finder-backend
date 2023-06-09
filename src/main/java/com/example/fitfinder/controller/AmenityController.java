package com.example.fitfinder.controller;

import com.example.fitfinder.models.Amenity;
import com.example.fitfinder.models.Equipment;
import com.example.fitfinder.service.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class AmenityController {

    @Autowired
    private AmenityService amenityService;

    @GetMapping(path = "/amenities")
    public List<Amenity> getAllAmenities(){
        return amenityService.getAllAmenities();
    }


    @GetMapping(path = "/gyms/{gymId}/amenities")
    public List<Amenity> getAllAmenitiesByGymId(@PathVariable Long gymId) {
        return amenityService.getAllAmenitiesByGymId(gymId);
    }

    // Functionality: Creates an amenity for gym
    // Path: http://localhost:8080/api/gyms/{gymId}/amenities
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/gyms/{gymId}/amenities")
    public Amenity createAmenityByGymId(@PathVariable Long gymId, @RequestBody Amenity amenityObject){
        return amenityService.createAmenityByGymId(gymId, amenityObject);
    }

    // Functionality: Updates amenity details for gym
    // Path: http://localhost:8080/api/gyms/{gymId}/amenities/{amenityId}
    @PutMapping(path = "/gyms/{gymId}/amenities/{amenityId}")
    public Amenity updateAmenityByGymId(@PathVariable Long gymId, @PathVariable Long amenityId, @RequestBody Amenity amenityObject){
        return amenityService.updateAmenityByGymId(gymId, amenityId, amenityObject);
    }

    // Functionality: Deletes an amenity for gym
    // Path: http://localhost:8080/api/gyms/{gymId}/amenities/{amenityId}
    @DeleteMapping(path = "/gyms/{gymId}/amenities/{amenityId}")
    public Amenity deleteAmenityByGymId(@PathVariable Long gymId, @PathVariable Long amenityId){
        return amenityService.deleteAmenityByGymId(gymId, amenityId);
    }
}
