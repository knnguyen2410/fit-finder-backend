package com.example.fitfinder.controller;

import com.example.fitfinder.models.Amenity;
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

    // Functionality: Gets all amenities
    // Path: http://localhost:8080/api/amenities
    /**
     * Retrieves all amenities.
     *
     * @return List of all amenities
     */
    @GetMapping(path = "/amenities")
    public List<Amenity> getAllAmenities(){
        return amenityService.getAllAmenities();
    }

    // Functionality: Gets all amenities for the gym
    // Path: http://localhost:8080/api/gyms/{gymId}/amenities
    /**
     * Retrieves all amenities for a specific gym.
     *
     * @param gymId ID of the gym
     * @return List of amenities for the specified gym
     */
    @GetMapping(path = "/gyms/{gymId}/amenities")
    public List<Amenity> getAllAmenitiesByGymId(@PathVariable Long gymId) {
        return amenityService.getAllAmenitiesByGymId(gymId);
    }

    // Functionality: Creates an amenity for gym
    // Path: http://localhost:8080/api/gyms/{gymId}/amenities
    /**
     * Creates a new amenity for a specific gym.
     *
     * @param gymId ID of the gym
     * @param amenityObject Amenity object to be created
     * @return The created amenity
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/gyms/{gymId}/amenities")
    public Amenity createAmenityByGymId(@PathVariable Long gymId, @RequestBody Amenity amenityObject){
        return amenityService.createAmenityByGymId(gymId, amenityObject);
    }

    // Functionality: Updates amenity details for gym
    // Path: http://localhost:8080/api/gyms/{gymId}/amenities/{amenityId}
    /**
     * Updates the details of an existing amenity for a specific gym.
     *
     * @param gymId ID of the gym
     * @param amenityId ID of the amenity
     * @param amenityObject Updated Amenity object
     * @return The updated amenity
     */
    @PutMapping(path = "/gyms/{gymId}/amenities/{amenityId}")
    public Amenity updateAmenityByGymId(@PathVariable Long gymId, @PathVariable Long amenityId, @RequestBody Amenity amenityObject){
        return amenityService.updateAmenityByGymId(gymId, amenityId, amenityObject);
    }

    // Functionality: Deletes an amenity for gym
    // Path: http://localhost:8080/api/gyms/{gymId}/amenities/{amenityId}
    /**
     * Deletes an existing amenity for a specific gym.
     *
     * @param gymId ID of the gym
     * @param amenityId ID of the amenity to be deleted
     * @return The deleted amenity
     */
    @DeleteMapping(path = "/gyms/{gymId}/amenities/{amenityId}")
    public Amenity deleteAmenityByGymId(@PathVariable Long gymId, @PathVariable Long amenityId){
        return amenityService.deleteAmenityByGymId(gymId, amenityId);
    }
}
