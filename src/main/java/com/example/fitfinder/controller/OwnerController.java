package com.example.fitfinder.controller;

import com.example.fitfinder.models.Gym;
import com.example.fitfinder.models.Owner;
import com.example.fitfinder.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    // Functionality: Creates owner account
    // Path: http://localhost:8080/api/owners/register
    @PostMapping(path = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Owner createOwner (@RequestBody @Valid Owner ownerObject){
        return ownerService.createOwner(ownerObject);
    }

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
