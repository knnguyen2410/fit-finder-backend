package com.example.fitfinder.service;

import com.example.fitfinder.exceptions.NotFoundException;
import com.example.fitfinder.models.Amenity;
import com.example.fitfinder.models.Equipment;
import com.example.fitfinder.models.Gym;
import com.example.fitfinder.repository.AmenityRepository;
import com.example.fitfinder.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmenityService {

    private AmenityRepository amenityRepository;

    private GymRepository gymRepository;

    @Autowired
    public void setAmenityRepository(AmenityRepository amenityRepository) {
        this.amenityRepository = amenityRepository;
    }

    @Autowired
    public void setGymRepository(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    public List<Amenity> getAllAmenitiesByGymId(Long gymId){
        Optional<Gym> gym = gymRepository.findById(gymId);
        if (gym.isPresent()){
            List<Amenity> amenityList = gym.get().getAmenityList();
            if (amenityList.size() == 0){
                throw new NotFoundException("No amenities found for gym with id " + gymId);
            } else {
                return amenityList;
            }
        } else {
            throw new NotFoundException("Gym with id " + gymId + " not found");
        }
    }
}
