package com.example.fitfinder.service;

import com.example.fitfinder.exceptions.NotFoundException;
import com.example.fitfinder.models.Gym;
import com.example.fitfinder.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GymService {

    private GymRepository gymRepository;

    @Autowired
    public void setGymRepository(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
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
