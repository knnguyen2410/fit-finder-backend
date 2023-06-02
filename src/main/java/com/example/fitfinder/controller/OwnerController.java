package com.example.fitfinder.controller;

import com.example.fitfinder.models.Gym;
import com.example.fitfinder.models.Owner;
import com.example.fitfinder.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    // Functionality: Returns owner account details
    // Path: http://localhost:8080/api/owners/{ownerId}
    @GetMapping(path = "/{ownerId}")
    public Optional<Owner> getOwnerById(@PathVariable Long ownerId){
        return ownerService.getOwnerById(ownerId);
    }

    // Functionality: Returns a list of all gyms belonging to owner account
    // Path: http://localhost:8080/api/owners/{ownerId}/gyms
    @GetMapping(path = "/{ownerId}/gyms")
    public List<Gym> getAllGymsByOwnerId(@PathVariable Long ownerId) {
        return ownerService.getAllGymsByOwnerId(ownerId);
    }
}
