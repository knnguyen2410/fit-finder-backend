package com.example.fitfinder.service;

import com.example.fitfinder.exceptions.AlreadyExistsException;
import com.example.fitfinder.exceptions.BadRequestException;
import com.example.fitfinder.exceptions.NotFoundException;
import com.example.fitfinder.models.Equipment;
import com.example.fitfinder.models.Gym;
import com.example.fitfinder.models.Owner;
import com.example.fitfinder.repository.EquipmentRepository;
import com.example.fitfinder.repository.GymRepository;
import com.example.fitfinder.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EquipmentService {

    private EquipmentRepository equipmentRepository;

    private GymRepository gymRepository;

    private OwnerRepository ownerRepository;

    private OwnerService ownerService;

    @Autowired
    public void setEquipmentRepository(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Autowired
    public void setGymRepository(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Autowired
    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
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

    public Equipment createEquipmentForGymId(Long gymId, Equipment equipmentObject) {
        Optional<Owner> owner = ownerRepository.findById(ownerService.getLoggedInOwner().getId());
        if (owner.isPresent()){
            Optional<Gym> gym = gymRepository.findGymByIdAndOwnerId(gymId, owner.get().getId());
            if (gym.isPresent()) {
                if (Objects.equals(equipmentObject.getName(), "") || equipmentObject.getName() == null) {
                    throw new BadRequestException("Equipment name is required");
                }
                if (equipmentRepository.existsByNameAndBrand(equipmentObject.getName(), equipmentObject.getBrand())){
                    throw new AlreadyExistsException("Equipment with name " + equipmentObject.getName() + " from brand " + equipmentObject.getBrand() + " already exists");
                }
                equipmentObject.setGym(gym.get());
                gym.get().getEquipmentList().add(equipmentObject);
                return equipmentRepository.save(equipmentObject);
            } else {
                throw new NotFoundException("Gym with id " + gymId + " belonging to owner with id " + owner.get().getId() + " not found");
            }
        } else {
            throw new NotFoundException("Owner with id " + owner.get().getId() + " not found");
        }
    }
}