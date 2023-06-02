package com.example.fitfinder.service;

import com.example.fitfinder.exceptions.AlreadyExistsException;
import com.example.fitfinder.exceptions.BadRequestException;
import com.example.fitfinder.exceptions.NotFoundException;
import com.example.fitfinder.models.Gym;
import com.example.fitfinder.models.Owner;
import com.example.fitfinder.repository.GymRepository;
import com.example.fitfinder.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OwnerService {

    private OwnerRepository ownerRepository;

    private GymRepository gymRepository;

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Autowired
    public void setGymRepository(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    public Owner createOwner(Owner ownerObject){
        // Check that the name field is not empty when updating the name
        if (Objects.equals(ownerObject.getName(), "") || ownerObject.getName() == null) {
            throw new BadRequestException("Owner name is required");
        }
        // Check that the email field is not empty when updating the email
        if (Objects.equals(ownerObject.getEmail(), "") || ownerObject.getEmail() == null) {
            throw new BadRequestException("Owner email is required");
        }
        // Check that the password field is not empty when updating the password
        if (Objects.equals(ownerObject.getPassword(), "") || ownerObject.getPassword() == null) {
            throw new BadRequestException("Owner password is required");
        }
        // Check the email does not exist in the database
        if (!ownerRepository.existsByEmail(ownerObject.getEmail())) {
            // Hash the password the user entered
            ownerObject.setPassword(ownerObject.getPassword());
            // Return the data for the newly created user
            return ownerRepository.save(ownerObject);
        } else {
            // Throw an error if the email already exists in the database
            throw new AlreadyExistsException("A gym owner with the email address " + ownerObject.getEmail() + " already exists");
        }
    }

    public Owner findOwnerByEmail(String email){
        return ownerRepository.findOwnerByEmail(email);
    }

    public Optional<Owner> getOwnerById(Long ownerId){
        Optional<Owner> owner = ownerRepository.findById(ownerId);
        if (owner.isPresent()){
            return owner;
        } else {
            throw new NotFoundException("Owner with id " + ownerId + " not found");
        }
    }

    public List<Gym> getAllGymsByOwnerId(Long ownerId){
        Optional<Owner> owner = ownerRepository.findById(ownerId);
        if (owner.isPresent()){
            List<Gym> gymList = owner.get().getGymList();
            if (gymList.size() == 0){
                throw new NotFoundException("No gyms found for owner with id " + ownerId);
            } else {
                return gymList;
            }
        } else {
            throw new NotFoundException("Owner with id " + ownerId + " not found");
        }
    }
}
