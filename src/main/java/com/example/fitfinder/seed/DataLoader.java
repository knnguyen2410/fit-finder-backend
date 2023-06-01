package com.example.fitfinder.seed;

import com.example.fitfinder.models.Amenity;
import com.example.fitfinder.models.Equipment;
import com.example.fitfinder.models.Gym;
import com.example.fitfinder.models.Owner;
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

    private void loadUserData(){
        if (ownerRepository.count() == 0 &&
            gymRepository.count() == 0 &&
            equipmentRepository.count() == 0 &&
            amenityRepository.count() == 0){

            // create owner
            Owner kim = new Owner(1L, "Kim Nguyen", "knnguyen2410@gmail.com", "password");

            // create gym
            Gym kimGym = new Gym(1L, "Perfect Fit", "Commercial Gym", "123 N. Street St.", "Chicago", "IL", 60654L, "Weekdays 5am - 10pm, Weekends 8am - 8pm", "(123) 123-1234", "New commercial gym in River North");

            // create equipment
            Equipment weightPlate45 = new Equipment(1L, "Weight", "Trustworthy Brand", "45lb weight plate (single)", 20L, "One (1) 45lb weight plate");
            Equipment barbell = new Equipment(2L, "Bar", "Trustworthy Brand", "1 inch barbell", 5L, "One (1) 1 inch barbell");
            Equipment flatBenchPress = new Equipment(3L, "Bench", "Trustworthy Brand", "Flat bench press", 5L, "One (1) flat branch press, non-adjustable");

            // create amenity
            Amenity basketballCourt = new Amenity(1L, "Recreational facility", "Sports", "Basketball Court", "New basketball court");
        }
    }
}
