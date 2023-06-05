package com.example.fitfinder.service;

import com.example.fitfinder.exceptions.AlreadyExistsException;
import com.example.fitfinder.exceptions.BadRequestException;
import com.example.fitfinder.exceptions.NotFoundException;
import com.example.fitfinder.models.Gym;
import com.example.fitfinder.models.Owner;
import com.example.fitfinder.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GymService {

    private GymRepository gymRepository;

    private OwnerService ownerService;

    @Autowired
    public void setGymRepository(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Autowired
    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public Gym createGym(Gym gymObject){
        if (gymRepository.existsByName(gymObject.getName()) && gymRepository.existsByAddressStreet(gymObject.getAddressStreet())){
            throw new AlreadyExistsException("Gym with the name " + gymObject.getName() + " and street address " + gymObject.getAddressStreet() + " already exists");
        } else {
            if (Objects.equals(gymObject.getName(), "") || gymObject.getName() == null) {
                throw new BadRequestException("Gym name is required");
            }
            if (Objects.equals(gymObject.getAddressStreet(), "") || gymObject.getAddressStreet() == null) {
                throw new BadRequestException("Gym street address is required");
            }
            gymObject.setOwner(ownerService.getLoggedInOwner());
            return gymRepository.save(gymObject);
        }
    }

    public List<Gym> getAllGyms(){
        List<Gym> allGyms = gymRepository.findAll();
        if (allGyms.size() == 0){
            throw new NotFoundException("No gyms found");
        } else {
            return allGyms;
        }
    }

    public Gym getGymById(Long gymId){
        Optional<Gym> gym = gymRepository.findById(gymId);
        if (gym.isPresent()){
            return gym.get();
        } else {
            throw new NotFoundException("Gym with id " + gymId + " not found");
        }
    }

    public Gym updateGymById(Long gymId, Gym gymObject){
        Optional<Gym> gym = gymRepository.findById(gymId);
        if (gym.isPresent()){
            if (gymObject.getName() != null && !gymObject.getName().isEmpty()){
                gym.get().setName(gymObject.getName());
            }
            if (gymObject.getCategory() != null && !gymObject.getCategory().isEmpty()){
                gym.get().setCategory(gymObject.getCategory());
            }
            if (gymObject.getAddressStreet() != null && !gymObject.getAddressStreet().isEmpty()){
                gym.get().setAddressStreet(gymObject.getAddressStreet());
            }
            if (gymObject.getAddressCity() != null && !gymObject.getAddressCity().isEmpty()){
                gym.get().setAddressCity(gymObject.getAddressCity());
            }
            if (gymObject.getAddressState() != null && !gymObject.getAddressState().isEmpty()){
                gym.get().setAddressState(gymObject.getAddressState());
            }
            if (gymObject.getAddressZip() != null){
                gym.get().setAddressZip(gymObject.getAddressZip());
            }
            if (gymObject.getHours() != null && !gymObject.getHours().isEmpty()){
                gym.get().setHours(gymObject.getHours());
            }
            if (gymObject.getPhone() != null && !gymObject.getPhone().isEmpty()){
                gym.get().setPhone(gymObject.getPhone());
            }
            if (gymObject.getDetails() != null && !gymObject.getDetails().isEmpty()){
                gym.get().setDetails(gymObject.getDetails());
            }
            return gymRepository.save(gym.get());
        } else {
            throw new NotFoundException("Gym with id " + gymId + " not found");
        }
    }
}
