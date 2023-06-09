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

    /**
     * The DataLoader class is responsible for seeding the database with initial data.
     * It loads user, gym, equipment, and amenity data into their respective repositories.
     */
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
            Gym perfectBoxing = new Gym(2L, "Perfect Boxing Gym", "Boxing Gym", "456 N. Street St.", "Chicago", "IL", 60654L, "Weekdays 5am - 10pm, Weekends 8am - 8pm", "(123) 123-1234", "New boxing gym in River North", "https://www.unanimousboxinggym.com/wp-content/uploads/2018/03/IMG_0298.jpg");
            Gym perfectMuayThai = new Gym(3L, "Perfect Muay Thai", "Muay Thai Gym", "789 N. Street St.", "Chicago", "IL", 60654L, "Weekdays 5am - 10pm, Weekends 8am - 8pm", "(123) 123-1234", "New Muay Thai gym in River North", "https://static.bangkokpost.com/media/content/dcx/2020/02/23/3537279.jpg");

            // create equipment
            Equipment weightPlate45 = new Equipment(1L, "Weight", "Trustworthy Brand", "45lb weight plate (single)", 20L, "One (1) 45lb weight plate", "https://assets.roguefitness.com/f_auto,q_auto,c_limit,w_1600,b_rgb:f8f8f8/catalog/Weightlifting%20Bars%20and%20Plates/Plates/Bumper%20Plates/IP0125/IP0125-WEB6_t27jkg.png");
            Equipment barbell = new Equipment(2L, "Bar", "Trustworthy Brand", "1 inch barbell", 5L, "One (1) 1 inch barbell", "https://i5.walmartimages.com/asr/894709d8-3902-48a6-92f1-f06a46ac1212_1.6e34ca45c1fa1c226f2d738f683bbb8f.jpeg");
            Equipment flatBench = new Equipment(3L, "Bench", "Trustworthy Brand", "Flat bench press", 5L, "One (1) flat branch press, non-adjustable", "https://assets.roguefitness.com/f_auto,q_auto,c_limit,w_1600,b_rgb:f8f8f8/catalog/Strength%20Equipment/Strength%20Training/Weight%20Benches/Flat%20Utility%20Benches/RA1362/RA1362-Textured-Pad-H_r6qelt.png");

            Equipment boxingGloves = new Equipment(4L, "Speed Bag", "Everlast", "Pro-style Training Gloves", 10L, "Pair of boxing gloves for training", "https://www.everlast.com/media/catalog/product/cache/d3a47dbc718700cd4253aa3639f28a90/4/2/4215_5.jpg");
            Equipment punchingBag = new Equipment(5L, "Heavy Bag", "Century", "Heavy Bag", 50L, "Heavy bag for punching and kicking drills", "https://cdn.shopify.com/s/files/1/0442/0429/4298/products/101615_1_800x.jpg?v=1642699599");
            Equipment jumpRope = new Equipment(6L, "Jump Rope", "Rogue Fitness", "Speed Jump Rope", 2L, "High-speed jump rope for cardio conditioning", "https://www.rei.com/media/b651fe34-bd7f-46ed-8fce-4d13fad31082.jpg?size=784x588");

            Equipment focusMitts = new Equipment(7L, "Focus Mitts", "Fairtex", "Focus Mitts", 3L, "Focus mitts for training", "https://cdn.shopify.com/s/files/1/0067/5076/5129/products/4117et4mWGL_500x.jpg?v=1605198280");
            Equipment thaiPads = new Equipment(8L, "Thai Pads", "Twins Special", "Muay Thai Kick Pads", 15L, "Pads for practicing strikes and kicks with a training partner", "https://cdn.shopify.com/s/files/1/0590/7487/1473/products/Kicking-pads-twins-muay-thai-6_720x.jpg?v=1630055361");
            Equipment shinGuards = new Equipment(9L, "Shin Guards", "Venum", "Muay Thai Shin Guards", 8L, "Protective shin guards for sparring and heavy bag work", "https://target.scene7.com/is/image/Target/GUEST_6f3b599e-4f73-4a61-b941-725bf2fccb61?wid=488&hei=488&fmt=pjpeg");

            // create amenity
            Amenity basketballCourt = new Amenity(1L, "Recreational facility", "Sports", "Basketball Court", 1L, "New basketball court", "https://www.lakeshoresf.com/wp-content/uploads/2021/03/Pick-up-Basketball-at-Illinois-Center-768x576.jpeg");
            Amenity rockClimbingWall = new Amenity(2L, "Adventure facility", "Rock Climbing", "Rock Climbing Wall", 1L, "Challenging rock climbing wall for climbers of all levels", "https://www.lakeshoresf.com/wp-content/gallery/ic-climbing/IC-Wall-Valerie-Kim-960x550.jpg");
            Amenity groupFitnessClasses = new Amenity(3L, "Fitness facility", "Group Fitness", "Group Fitness Classes", 1L, "Dedicated studio for group fitness classes such as Zumba, HIIT, and Pilates", "https://bestfitnessgyms.com/danvers-ma/files/2019/06/best-gyms-hot-yoga-studio-group-fitness-near-me-1070x840.jpg");

            Amenity swimmingPool = new Amenity(4L, "Recreational facility", "Swimming", "Swimming Pool", 1L, "Indoor swimming pool with lap lanes", "https://chuzefitness.com/wp-content/uploads/MG_5562.jpg");
            Amenity indoorTrack = new Amenity(5L, "Recreational facility", "Running", "Indoor Track", 1L, "Indoor track for running and jogging", "https://www.bdcnetwork.com/sites/bdc/files/Gately%20Park%20Track%20Interior_0.jpg");
            Amenity juiceBar = new Amenity(6L, "Refreshment facility", "Juice Bar", "Juice Bar", 1L, "Cozy juice bar offering nutritious and refreshing beverages", "https://bestfitnessgyms.com/danvers-ma/files/2019/06/juice-cafe-best-gym-near-me-danvers-1070x840.jpg");

            Amenity sauna = new Amenity(7L, "Wellness facility", "Sauna", "Sauna Room", 1L, "Relaxing sauna room for detoxification and relaxation", "https://media.istockphoto.com/id/517419408/photo/sauna.jpg?s=612x612&w=0&k=20&c=KR_zH8fEEVVJxHnwWpNFWffhXCITy3QEB1wSdJEtoqE=");
            Amenity steamRoom = new Amenity(8L, "Wellness facility", "Spa", "Steam Room", 1L, "Relaxing steam room for detoxification and relaxation", "https://www.skyfitnesschicago.com/wp-content/uploads/2019/01/Sky-Fitness-Chicago-Amenities-Sauna.jpg");
            Amenity yogaStudio = new Amenity(9L, "Fitness facility", "Yoga", "Yoga Studio", 1L, "Spacious and well-equipped yoga studio for yoga and meditation classes", "https://media.timeout.com/images/105578653/750/562/image.jpg");

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

            boxingGloves.setGym(perfectBoxing);
            punchingBag.setGym(perfectBoxing);
            jumpRope.setGym(perfectBoxing);
            equipmentRepository.save(boxingGloves);
            equipmentRepository.save(punchingBag);
            equipmentRepository.save(jumpRope);

            focusMitts.setGym(perfectMuayThai);
            thaiPads.setGym(perfectMuayThai);
            shinGuards.setGym(perfectMuayThai);
            equipmentRepository.save(focusMitts);
            equipmentRepository.save(thaiPads);
            equipmentRepository.save(shinGuards);

            // set gym for amenity
            basketballCourt.setGym(perfectFit);
            rockClimbingWall.setGym(perfectFit);
            groupFitnessClasses.setGym(perfectFit);
            amenityRepository.save(basketballCourt);
            amenityRepository.save(rockClimbingWall);
            amenityRepository.save(groupFitnessClasses);

            swimmingPool.setGym(perfectBoxing);
            indoorTrack.setGym(perfectBoxing);
            juiceBar.setGym(perfectBoxing);
            amenityRepository.save(swimmingPool);
            amenityRepository.save(indoorTrack);
            amenityRepository.save(juiceBar);

            sauna.setGym(perfectMuayThai);
            steamRoom.setGym(perfectMuayThai);
            yogaStudio.setGym(perfectMuayThai);
            amenityRepository.save(sauna);
            amenityRepository.save(steamRoom);
            amenityRepository.save(yogaStudio);

            // set gym list for owner
            ArrayList<Gym> kimGymList = new ArrayList<>();
            kimGymList.add(perfectFit);
            kim.setGymList(kimGymList);
            ownerRepository.save(kim);

            ArrayList<Gym> samGymList = new ArrayList<>();
            samGymList.add(perfectBoxing);
            sam.setGymList(samGymList);
            ownerRepository.save(sam);

            ArrayList<Gym> ashGymList = new ArrayList<>();
            ashGymList.add(perfectMuayThai);
            ash.setGymList(ashGymList);
            ownerRepository.save(ash);

            // set equipment list for gym
            ArrayList<Equipment> perfectFitEquipmentList = new ArrayList<>();
            perfectFitEquipmentList.add(weightPlate45);
            perfectFitEquipmentList.add(barbell);
            perfectFitEquipmentList.add(flatBench);
            perfectFit.setEquipmentList(perfectFitEquipmentList);

            ArrayList<Equipment> perfectBoxingEquipmentList = new ArrayList<>();
            perfectBoxingEquipmentList.add(boxingGloves);
            perfectBoxingEquipmentList.add(punchingBag);
            perfectBoxingEquipmentList.add(jumpRope);
            perfectBoxing.setEquipmentList(perfectBoxingEquipmentList);

            ArrayList<Equipment> perfectMuayThaiEquipmentList = new ArrayList<>();
            perfectMuayThaiEquipmentList.add(focusMitts);
            perfectMuayThaiEquipmentList.add(thaiPads);
            perfectMuayThaiEquipmentList.add(shinGuards);
            perfectMuayThai.setEquipmentList(perfectMuayThaiEquipmentList);

            // set amenity list for gym
            ArrayList<Amenity> perfectFitAmenityList = new ArrayList<>();
            perfectFitAmenityList.add(basketballCourt);
            perfectFitAmenityList.add(rockClimbingWall);
            perfectFitAmenityList.add(groupFitnessClasses);
            perfectFit.setAmenityList(perfectFitAmenityList);

            ArrayList<Amenity> perfectBoxingAmenityList = new ArrayList<>();
            perfectBoxingAmenityList.add(swimmingPool);
            perfectBoxingAmenityList.add(indoorTrack);
            perfectBoxingAmenityList.add(juiceBar);
            perfectBoxing.setAmenityList(perfectBoxingAmenityList);

            ArrayList<Amenity> perfectMuayThaiAmenityList = new ArrayList<>();
            perfectMuayThaiAmenityList.add(sauna);
            perfectMuayThaiAmenityList.add(steamRoom);
            perfectMuayThaiAmenityList.add(yogaStudio);
            perfectMuayThai.setAmenityList(perfectMuayThaiAmenityList);

            gymRepository.save(perfectFit);
            gymRepository.save(perfectBoxing);
            gymRepository.save(perfectMuayThai);
        }
    }
}
