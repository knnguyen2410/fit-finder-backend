package com.example.fitfinder.service;

import com.example.fitfinder.exceptions.AlreadyExistsException;
import com.example.fitfinder.exceptions.BadRequestException;
import com.example.fitfinder.exceptions.NotFoundException;
import com.example.fitfinder.models.Amenity;
import com.example.fitfinder.models.Gym;
import com.example.fitfinder.models.Owner;
import com.example.fitfinder.repository.AmenityRepository;
import com.example.fitfinder.repository.GymRepository;
import com.example.fitfinder.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AmenityService {

    private AmenityRepository amenityRepository;

    private GymRepository gymRepository;

    private OwnerRepository ownerRepository;

    private OwnerService ownerService;

    @Autowired
    public void setAmenityRepository(AmenityRepository amenityRepository) {
        this.amenityRepository = amenityRepository;
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

    /**
     * Retrieves a list of all amenities.
     *
     * @return A list of all amenities.
     * @throws NotFoundException If no amenities are found.
     */
    public List<Amenity> getAllAmenities(){
        List<Amenity> allAmenities = amenityRepository.findAll();
        if (allAmenities.size() == 0){
            throw new NotFoundException("No amenities found");
        } else {
            return allAmenities;
        }
    }

    /**
     * Retrieves a list of amenities associated with a specific gym.
     *
     * @param gymId The ID of the gym.
     * @return A list of amenities associated with the specified gym.
     * @throws NotFoundException If no amenities are found for the specified gym.
     */
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

    /**
     * Creates a new amenity associated with a specific gym.
     *
     * @param gymId The ID of the gym.
     * @param amenityObject The amenity object to create.
     * @return The created amenity.
     * @throws BadRequestException If the amenity name is empty or null.
     * @throws AlreadyExistsException If an amenity with the same name already exists.
     * @throws NotFoundException If the gym or owner is not found.
     */
    public Amenity createAmenityByGymId(Long gymId, Amenity amenityObject) {
        Optional<Owner> owner = ownerRepository.findById(ownerService.getLoggedInOwner().getId());
        if (owner.isPresent()){
            Optional<Gym> gym = gymRepository.findGymByIdAndOwnerId(gymId, owner.get().getId());
            if (gym.isPresent()) {
                if (Objects.equals(amenityObject.getName(), "") || amenityObject.getName() == null) {
                    throw new BadRequestException("Amenity name is required");
                }
                if (amenityRepository.existsByName(amenityObject.getName())){
                    throw new AlreadyExistsException("Amenity with name " + amenityObject.getName() + " already exists");
                }
                amenityObject.setGym(gym.get());
                gym.get().getAmenityList().add(amenityObject);
                return amenityRepository.save(amenityObject);
            } else {
                throw new NotFoundException("Gym with id " + gymId + " belonging to owner with id " + owner.get().getId() + " not found");
            }
        } else {
            throw new NotFoundException("Owner with id " + owner.get().getId() + " not found");
        }
    }

    /**
     * Updates an existing amenity associated with a specific gym.
     *
     * @param gymId The ID of the gym.
     * @param amenityId The ID of the amenity to update.
     * @param amenityObject The updated amenity object.
     * @return The updated amenity.
     * @throws NotFoundException If the gym, owner, or amenity is not found.
     */
    public Amenity updateAmenityByGymId(Long gymId, Long amenityId, Amenity amenityObject){
        Optional<Owner> owner = ownerRepository.findById(ownerService.getLoggedInOwner().getId());
        if (owner.isPresent()){
            Optional<Gym> gym = gymRepository.findGymByIdAndOwnerId(gymId, owner.get().getId());
            if (gym.isPresent()) {
                Optional<Amenity> amenity = amenityRepository.findAmenityByIdAndGymId(amenityId, gymId);
                if (amenity.isPresent()){
                    if (amenityObject.getCategory() != null && !amenityObject.getCategory().isEmpty()){
                        amenity.get().setCategory(amenityObject.getCategory());
                    }
                    if (amenityObject.getSubcategory() != null && !amenityObject.getSubcategory().isEmpty()){
                        amenity.get().setSubcategory(amenityObject.getSubcategory());
                    }
                    if (amenityObject.getName() != null && !amenityObject.getName().isEmpty()){
                        amenity.get().setName(amenityObject.getName());
                    }
                    if (amenityObject.getQuantity() != null){
                        amenity.get().setQuantity(amenityObject.getQuantity());
                    }
                    if (amenityObject.getDetails() != null && !amenityObject.getDetails().isEmpty()){
                        amenity.get().setDetails(amenityObject.getDetails());
                    }
                    if (amenityObject.getImage() != null && !amenityObject.getImage().isEmpty()){
                        amenity.get().setImage(amenityObject.getImage());
                    }
                    return amenityRepository.save(amenity.get());
                } else {
                    throw new NotFoundException("Amenity with id " + amenityId + " not found");
                }
            } else {
                throw new NotFoundException("Gym with id " + gymId + " belonging to owner with id " + owner.get().getId() + " not found");
            }
        } else {
            throw new NotFoundException("Owner with id " + owner.get().getId() + " not found");
        }
    }

    /**
     * Deletes an existing amenity associated with a specific gym.
     *
     * @param gymId The ID of the gym.
     * @param amenityId The ID of the amenity to delete.
     * @return The deleted amenity.
     * @throws NotFoundException If the gym, owner, or amenity is not found.
     */
    public Amenity deleteAmenityByGymId(Long gymId, Long amenityId) {
        Optional<Owner> owner = ownerRepository.findById(ownerService.getLoggedInOwner().getId());
        if (owner.isPresent()) {
            Optional<Gym> gym = gymRepository.findGymByIdAndOwnerId(gymId, owner.get().getId());
            if (gym.isPresent()) {
                Optional<Amenity> amenity = amenityRepository.findAmenityByIdAndGymId(amenityId, gymId);
                if (amenity.isPresent()) {
                    amenityRepository.delete(amenity.get());
                    return amenity.get();
                } else {
                    throw new NotFoundException("Amenity with id " + amenityId + " not found");
                }
            } else {
                throw new NotFoundException("Gym with id " + gymId + " belonging to owner with id " + owner.get().getId() + " not found");
            }
        } else {
            throw new NotFoundException("Owner with id " + owner.get().getId() + " not found");
        }
    }
}
