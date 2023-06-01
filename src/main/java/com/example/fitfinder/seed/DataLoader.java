package com.example.fitfinder.seed;

import com.example.fitfinder.repository.AmenityRepository;
import com.example.fitfinder.repository.EquipmentRepository;
import com.example.fitfinder.repository.GymRepository;
import com.example.fitfinder.repository.OwnerRepository;
import com.example.fitfinder.service.AmenityService;
import com.example.fitfinder.service.EquipmentService;
import com.example.fitfinder.service.GymService;
import com.example.fitfinder.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private GymService gymService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private AmenityService amenityService;

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private GymRepository gymRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private AmenityRepository amenityRepository;

    @Override
    public void run(String... args) throws Exception{
        loadUserData();
    }

    private void loadUserData(){}

}
