package com.example.fitfinder.service;

import com.example.fitfinder.exceptions.AlreadyExistsException;
import com.example.fitfinder.exceptions.BadRequestException;
import com.example.fitfinder.exceptions.NotFoundException;
import com.example.fitfinder.models.Gym;
import com.example.fitfinder.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GymService {

    private GymRepository gymRepository;

    @Autowired
    public void setGymRepository(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
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
}
