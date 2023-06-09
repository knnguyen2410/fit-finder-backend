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

    private OwnerService ownerService;

    @Autowired
    public void setGymRepository(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Autowired
    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * Creates a new gym.
     *
     * @param gymObject The gym object to create.
     * @return The created gym.
     * @throws AlreadyExistsException If a gym with the same name and address already exists.
     * @throws BadRequestException   If the gym name or street address is empty or null.
     */
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

    /**
     * Retrieves a list of all gyms.
     *
     * @return A list of all gyms.
     * @throws NotFoundException If no gyms are found.
     */
    public List<Gym> getAllGyms(){
        List<Gym> allGyms = gymRepository.findAll();
        if (allGyms.size() == 0){
            throw new NotFoundException("No gyms found");
        } else {
            return allGyms;
        }
    }

    /**
     * Retrieves a gym by its ID.
     *
     * @param gymId The ID of the gym.
     * @return The gym with the specified ID.
     * @throws NotFoundException If the gym with the specified ID is not found.
     */
    public Gym getGymById(Long gymId){
        Optional<Gym> gym = gymRepository.findById(gymId);
        if (gym.isPresent()){
            return gym.get();
        } else {
            throw new NotFoundException("Gym with id " + gymId + " not found");
        }
    }

    /**
     * Updates an existing gym by its ID.
     *
     * @param gymId      The ID of the gym to update.
     * @param gymObject  The updated gym object.
     * @return The updated gym.
     * @throws NotFoundException If the gym with the specified ID is not found.
     */
    public Gym updateGymById(Long gymId, Gym gymObject){
        Optional<Gym> gym = gymRepository.findGymByIdAndOwnerId(gymId, OwnerService.getLoggedInOwner().getId());
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
            if (gymObject.getImage() != null && !gymObject.getImage().isEmpty()){
                gym.get().setImage(gymObject.getImage());
            }
            return gymRepository.save(gym.get());
        } else {
            throw new NotFoundException("Gym with id " + gymId + " not found");
        }
    }

    /**
     * Deletes an existing gym by its ID.
     *
     * @param gymId The ID of the gym to delete.
     * @return The deleted gym.
     * @throws NotFoundException If the gym with the specified ID is not found.
     */
    public Gym deleteGymById(Long gymId){
        Optional<Gym> gym = gymRepository.findGymByIdAndOwnerId(gymId, OwnerService.getLoggedInOwner().getId());
        if (gym.isPresent()){
            gymRepository.delete(gym.get());
            return gym.get();
        } else {
            throw new NotFoundException("Gym with id " + gymId + " not found");
        }
    }
}
