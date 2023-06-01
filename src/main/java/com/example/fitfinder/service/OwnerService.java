package com.example.fitfinder.service;

import com.example.fitfinder.exceptions.AlreadyExistsException;
import com.example.fitfinder.exceptions.BadRequestException;
import com.example.fitfinder.models.Owner;
import com.example.fitfinder.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OwnerService {
    private OwnerRepository ownerRepository;

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner createOwner(Owner ownerObject){
        // Check that the name field is not empty when updating the name
        if (Objects.equals(ownerObject.getName(), "") || ownerObject.getName() == null) {
            throw new BadRequestException("User name is required");
        }
        // Check that the email field is not empty when updating the email
        if (Objects.equals(ownerObject.getEmail(), "") || ownerObject.getEmail() == null) {
            throw new BadRequestException("User email is required");
        }
        // Check that the password field is not empty when updating the password
        if (Objects.equals(ownerObject.getPassword(), "") || ownerObject.getPassword() == null) {
            throw new BadRequestException("User password is required");
        }
        // Check the email does not exist in the database
        if (!ownerRepository.existsByEmail(ownerObject.getEmail())) {
            // Hash the password the user entered
            ownerObject.setPassword(ownerObject.getPassword());
            // Return the data for the newly created user
            return ownerRepository.save(ownerObject);
        } else {
            // Throw an error if the email already exists in the database
            throw new AlreadyExistsException("A gym owner with email address " + ownerObject.getEmail() + " already exists");
        }
    }
}
