package com.example.fitfinder.service;

import com.example.fitfinder.exceptions.NotFoundException;
import com.example.fitfinder.models.Equipment;
import com.example.fitfinder.models.Gym;
import com.example.fitfinder.repository.EquipmentRepository;
import com.example.fitfinder.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    private EquipmentRepository equipmentRepository;

    private GymRepository gymRepository;

    @Autowired
    public void setEquipmentRepository(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Autowired
    public void setGymRepository(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    public List<Equipment> getAllEquipmentByGymId(Long gymId){
        Optional<Gym> gym = gymRepository.findById(gymId);
        if (gym.isPresent()){
            List<Equipment> equipmentList = gym.get().getEquipmentList();
            if (equipmentList.size() == 0){
                throw new NotFoundException("No equipment found for gym with id " + gymId);
            } else {
                return equipmentList;
            }
        } else {
            throw new NotFoundException("Gym with id " + gymId + " not found");
        }
    }
}
