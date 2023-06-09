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

    // Functionality: Creates a gym
    // Path: http://localhost:8080/api/gyms
    /**
     * Creates a gym.
     *
     * @param gymObject The gym object containing the details of the gym to be created.
     * @return The created gym object.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public Gym createGym(@RequestBody Gym gymObject){
        return gymService.createGym(gymObject);
    }

    // Functionality: Gets all gyms
    // Path: http://localhost:8080/api/gyms
    /**
     * Gets all gyms.
     *
     * @return A list of all gyms.
     */
    @GetMapping(path = "")
    public List<Gym> getAllGyms(){
        return gymService.getAllGyms();
    }

    // Functionality: Updates gym details
    // Path: http://localhost:8080/api/gyms/{gymId}
    /**
     * Gets a gym by its ID.
     *
     * @param gymId The ID of the gym.
     * @return The gym object corresponding to the given ID.
     */
    @GetMapping(path = "/{gymId}")
    public Gym getGymById(@PathVariable Long gymId){
        return gymService.getGymById(gymId);
    }

    // Functionality: Updates gym details
    // Path: http://localhost:8080/api/gyms/{gymId}
    /**
     * Updates gym details.
     *
     * @param gymId The ID of the gym to be updated.
     * @param gymObject The updated gym object containing the new details.
     * @return The updated gym object.
     */
    @PutMapping(path = "/{gymId}")
    public Gym updateGymById(@PathVariable Long gymId, @RequestBody Gym gymObject){
        return gymService.updateGymById(gymId, gymObject);
    }

    // Functionality: Deletes gym
    // Path: http://localhost:8080/api/gyms/{gymId}
    /**
     * Deletes a gym by its ID.
     *
     * @param gymId The ID of the gym to be deleted.
     * @return The deleted gym object.
     */
    @DeleteMapping(path = "/{gymId}")
    public Gym deleteGymById(@PathVariable Long gymId){
        return gymService.deleteGymById(gymId);
    }
}
