package com.example.fitfinder.service;

import com.example.fitfinder.exceptions.AlreadyExistsException;
import com.example.fitfinder.exceptions.BadRequestException;
import com.example.fitfinder.exceptions.NotFoundException;
import com.example.fitfinder.exceptions.UnauthorizedException;
import com.example.fitfinder.models.Gym;
import com.example.fitfinder.models.Owner;
import com.example.fitfinder.models.login.LoginRequest;
import com.example.fitfinder.models.login.LoginResponse;
import com.example.fitfinder.repository.GymRepository;
import com.example.fitfinder.repository.OwnerRepository;
import com.example.fitfinder.security.JWTUtils;
import com.example.fitfinder.security.MyOwnerDetails;
import com.example.fitfinder.security.MyOwnerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OwnerService {

    private OwnerRepository ownerRepository;
    private GymRepository gymRepository;
    private final PasswordEncoder passwordEncoder;
    private JWTUtils jwtUtils;
    private AuthenticationManager authenticationManager;
    private MyOwnerDetails myOwnerDetails;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository,
                        GymRepository gymRepository,
                        @Lazy PasswordEncoder passwordEncoder,
                        JWTUtils jwtUtils,
                        @Lazy AuthenticationManager authenticationManager,
                        @Lazy MyOwnerDetails myOwnerDetails) {
        this.ownerRepository = ownerRepository;
        this.gymRepository = gymRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.myOwnerDetails = myOwnerDetails;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Owner getLoggedInOwner() {
        MyOwnerDetails ownerDetails = (MyOwnerDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Check that there is a logged-in user
        if (ownerDetails.getOwner() == null || ownerDetails.getUsername().isEmpty() || ownerDetails.getUsername() == null) {
            // Return an error if the user is not found
            throw new UnauthorizedException("Unauthorized");
        }
        // Return the data for the logged-in user
        return ownerDetails.getOwner();
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
            ownerObject.setPassword(passwordEncoder.encode(ownerObject.getPassword()));
            // Return the data for the newly created user
            return ownerRepository.save(ownerObject);
        } else {
            // Throw an error if the email already exists in the database
            throw new AlreadyExistsException("A gym owner with the email address " + ownerObject.getEmail() + " already exists");
        }
    }

    public ResponseEntity<?> loginOwner(LoginRequest loginRequest) {
        try {
            // Authenticates the owner by checking the email and password provided
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            // Sets the authenticated user in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Obtains the user's details after authentication
            myOwnerDetails = (MyOwnerDetails) authentication.getPrincipal();
            // Generate a JWT key for the authenticated user
            final String JWT = jwtUtils.generateJwtToken(myOwnerDetails);
            // Return the JWT key
            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (Exception e) {
            // Returns a 401 status code if the authentication fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Error : email or password is incorrect"));
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

    public Owner updateOwnerById(Long ownerId, Owner ownerObject){
        Optional<Owner> owner = ownerRepository.findById(getLoggedInOwner().getId());
        if (owner.isPresent()){
            if (ownerObject.getName() != null && !ownerObject.getName().isEmpty()){
                owner.get().setName(ownerObject.getName());
            }
            if (ownerObject.getEmail() != null && !ownerObject.getEmail().isEmpty()){
                owner.get().setEmail(ownerObject.getEmail());
            }
            if (ownerObject.getPassword() != null && !ownerObject.getPassword().isEmpty()){
                owner.get().setPassword(passwordEncoder.encode(ownerObject.getPassword()));
            }
            if (ownerObject.getImage() != null && !ownerObject.getImage().isEmpty()){
                owner.get().setImage(ownerObject.getImage());
            }
            return ownerRepository.save(owner.get());
        } else {
            throw new NotFoundException("Owner with id " + ownerId + " not found");
        }
    }

    public Owner deleteOwnerById(Long ownerId){
        Optional<Owner> owner = ownerRepository.findById(getLoggedInOwner().getId());
        if (owner.isPresent()){
            ownerRepository.delete(owner.get());
            return owner.get();
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
