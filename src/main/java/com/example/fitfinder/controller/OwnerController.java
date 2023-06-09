package com.example.fitfinder.controller;

import com.example.fitfinder.models.Gym;
import com.example.fitfinder.models.Owner;
import com.example.fitfinder.models.login.LoginRequest;
import com.example.fitfinder.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    /**
     * Creates an owner account.
     *
     * @param ownerObject The owner object containing the details of the owner to be created.
     * @return The created owner object.
     */
    @PostMapping(path = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Owner createOwner (@RequestBody @Valid Owner ownerObject){
        return ownerService.createOwner(ownerObject);
    }

    // Functionality: Logs into owner account
    // Path: http://localhost:8080/api/owners/login
    /**
     * Logs into an owner account.
     *
     * @param loginRequest The login request object containing the login credentials.
     * @return A response entity with authentication details and status.
     */
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginOwner(@RequestBody LoginRequest loginRequest) {
        return ownerService.loginOwner(loginRequest);
    }

    // Functionality: Gets all owners
    // Path: http://localhost:8080/api/owners
    /**
     * Gets all owners.
     *
     * @return A list of all owners.
     */
    @GetMapping(path = "")
    public List<Owner> getAllOwners(){
        return ownerService.getAllOwners();
    }

    // Functionality: Returns owner account details
    // Path: http://localhost:8080/api/owners/{ownerId}
    /**
     * Returns owner account details by ID.
     *
     * @param ownerId The ID of the owner.
     * @return An optional owner object corresponding to the given ID.
     */
    @GetMapping(path = "/{ownerId}")
    public Optional<Owner> getOwnerById(@PathVariable Long ownerId){
        return ownerService.getOwnerById(ownerId);
    }

    // Functionality: Updates owner account details
    // Path: http://localhost:8080/api/owners/{ownerId}
    /**
     * Updates owner account details.
     *
     * @param ownerId     The ID of the owner to be updated.
     * @param ownerObject The updated owner object containing the new details.
     * @return The updated owner object.
     */
    @PutMapping(path = "/{ownerId}")
    public Owner updateOwnerById(@PathVariable Long ownerId, @RequestBody Owner ownerObject){
        return ownerService.updateOwnerById(ownerId, ownerObject);
    }

    // Functionality: Deletes owner account
    // Path: http://localhost:8080/api/owners/{ownerId}
    /**
     * Deletes an owner account by ID.
     *
     * @param ownerId The ID of the owner to be deleted.
     * @return The deleted owner object.
     */
    @DeleteMapping(path = "/{ownerId}")
    public Owner deleteOwnerById(@PathVariable Long ownerId){
        return ownerService.deleteOwnerById(ownerId);
    }


    // Functionality: Returns a list of all gyms belonging to owner account
    // Path: http://localhost:8080/api/owners/{ownerId}/gyms
    /**
     * Returns a list of all gyms belonging to an owner account.
     *
     * @param ownerId The ID of the owner.
     * @return A list of gyms owned by the owner.
     */
    @GetMapping(path = "/{ownerId}/gyms")
    public List<Gym> getAllGymsByOwnerId(@PathVariable Long ownerId) {
        return ownerService.getAllGymsByOwnerId(ownerId);
    }
}
