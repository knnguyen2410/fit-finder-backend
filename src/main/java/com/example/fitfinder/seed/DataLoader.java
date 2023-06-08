package com.example.fitfinder.seed;

import com.example.fitfinder.models.Amenity;
import com.example.fitfinder.models.Equipment;
import com.example.fitfinder.models.Gym;
import com.example.fitfinder.models.Owner;
import com.example.fitfinder.repository.AmenityRepository;
import com.example.fitfinder.repository.EquipmentRepository;
import com.example.fitfinder.repository.GymRepository;
import com.example.fitfinder.repository.OwnerRepository;
import com.example.fitfinder.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private AmenityRepository amenityRepository;

    @Override
    public void run(String ...args) throws Exception {
        loadUserData();
    }

    private void loadUserData(){
        if (ownerRepository.count() == 0 &&
            gymRepository.count() == 0 &&
            equipmentRepository.count() == 0 &&
            amenityRepository.count() == 0){

            // create owner
            Owner kim = new Owner(1L, "Kim Possible", "kim@gmail.com", "p", "https://media.istockphoto.com/id/856797530/photo/portrait-of-a-beautiful-woman-at-the-gym.jpg?s=612x612&w=0&k=20&c=0wMa1MYxt6HHamjd66d5__XGAKbJFDFQyu9LCloRsYU=");
            Owner sam = new Owner(2L, "Sam Manson", "sam@gmail.com", "p", "https://www.thimble.com/wp-content/uploads/2022/05/Personal-Trainer-Salary-Guide.jpg");
            Owner ash = new Owner(3L, "Ash Ketchum", "ash@gmail.com", "p", "https://hips.hearstapps.com/hmg-prod/images/mh-trainer-2-1533576998.png");
            ownerService.createOwner(kim);
            ownerService.createOwner(sam);
            ownerService.createOwner(ash);

            // create gym
            Gym perfectFit = new Gym(1L, "Perfect Fit", "Commercial Gym", "123 N. Street St.", "Chicago", "IL", 60654L, "Weekdays 5am - 10pm, Weekends 8am - 8pm", "(123) 123-1234", "New commercial gym in River North", "https://i.pinimg.com/originals/7c/c1/e3/7cc1e3d2c4d85454b3bb419f89dee043.jpg");
            Gym perfectBoxing = new Gym(2L, "Perfect Boxing Gym", "Boxing Gym", "456 N. Street St.", "Chicago", "IL", 60654L, "Weekdays 5am - 10pm, Weekends 8am - 8pm", "(123) 123-1234", "New boxing gym in River North", "https://static.giggster.com/images/location/6f821557-683c-46a3-949b-6523a604e802/082c5c51-25f9-4bd0-9d6a-b8b7419d0ab7/mid_x3.jpeg");
            Gym perfectMuayThai = new Gym(3L, "Perfect Muay Thai", "Muay Thai Gym", "789 N. Street St.", "Chicago", "IL", 60654L, "Weekdays 5am - 10pm, Weekends 8am - 8pm", "(123) 123-1234", "New Muay Thai gym in River North", "https://static.bangkokpost.com/media/content/dcx/2020/02/23/3537279.jpg");

            // create equipment
            Equipment weightPlate45 = new Equipment(1L, "Weight", "Trustworthy Brand", "45lb weight plate (single)", 20L, "One (1) 45lb weight plate", "https://assets.roguefitness.com/f_auto,q_auto,c_limit,w_1600,b_rgb:f8f8f8/catalog/Weightlifting%20Bars%20and%20Plates/Plates/Bumper%20Plates/IP0125/IP0125-WEB6_t27jkg.png");
            Equipment barbell = new Equipment(2L, "Bar", "Trustworthy Brand", "1 inch barbell", 5L, "One (1) 1 inch barbell", "https://assets.roguefitness.com/f_auto,q_auto,c_limit,w_1600,b_rgb:f8f8f8/catalog/Weightlifting%20Bars%20and%20Plates/Barbells/Mens%2020KG%20Barbells/RA0586-BLBR/RA0586-BLBR-WEB3_x8ayz1.png");
            Equipment flatBench = new Equipment(3L, "Bench", "Trustworthy Brand", "Flat bench press", 5L, "One (1) flat branch press, non-adjustable", "https://assets.roguefitness.com/f_auto,q_auto,c_limit,w_1600,b_rgb:f8f8f8/catalog/Strength%20Equipment/Strength%20Training/Weight%20Benches/Flat%20Utility%20Benches/RA1362/RA1362-Textured-Pad-H_r6qelt.png");

            // create amenity
            Amenity basketballCourt = new Amenity(1L, "Recreational facility", "Sports", "Basketball Court", 1L, "New basketball court", "https://www.lakeshoresf.com/wp-content/uploads/2021/03/Pick-up-Basketball-at-Illinois-Center-768x576.jpeg");

            // set owner for gym
            perfectFit.setOwner(kim);
            perfectBoxing.setOwner(sam);
            perfectMuayThai.setOwner(ash);
            gymRepository.save(perfectFit);
            gymRepository.save(perfectBoxing);
            gymRepository.save(perfectMuayThai);

            // set gym for equipment
            weightPlate45.setGym(perfectFit);
            barbell.setGym(perfectFit);
            flatBench.setGym(perfectFit);
            equipmentRepository.save(weightPlate45);
            equipmentRepository.save(barbell);
            equipmentRepository.save(flatBench);

            // set gym for amenity
            basketballCourt.setGym(perfectFit);
            amenityRepository.save(basketballCourt);

            // set gym list for owner
            ArrayList<Gym> kimGymList = new ArrayList<>();
            kimGymList.add(perfectFit);
            kim.setGymList(kimGymList);
            ownerRepository.save(kim);

            // set equipment list for gym
            ArrayList<Equipment> perfectFitEquipmentList = new ArrayList<>();
            perfectFitEquipmentList.add(weightPlate45);
            perfectFitEquipmentList.add(barbell);
            perfectFitEquipmentList.add(flatBench);
            perfectFit.setEquipmentList(perfectFitEquipmentList);

            // set amenity list for gym
            ArrayList<Amenity> perfectFitAmenityList = new ArrayList<>();
            perfectFitAmenityList.add(basketballCourt);
            perfectFit.setAmenityList(perfectFitAmenityList);

            gymRepository.save(perfectFit);
        }
    }
}
