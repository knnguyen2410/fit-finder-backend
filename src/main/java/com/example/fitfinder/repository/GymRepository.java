package com.example.fitfinder.repository;

import com.example.fitfinder.models.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {

    /**
     * Finds gym by its ID.
     *
     * @param gymId The ID of the gym.
     * @return An optional containing the found gym or empty if not found.
     */
    Optional<Gym> findById(Long gymId);

    /**
     * Checks if gym exists with the given street address.
     *
     * @param addressStreet The street address of the gym.
     * @return true if gym with the given street address exists, false otherwise.
     */
    boolean existsByAddressStreet(String addressStreet);

    /**
     * Checks if gym exists with the given name.
     *
     * @param name The name of the gym.
     * @return true if gym with the given name exists, false otherwise.
     */
    boolean existsByName(String name);

    /**
     * Finds gym by its ID and owner ID.
     *
     * @param gymId The ID of the gym.
     * @param ownerId The ID of the owner.
     * @return An optional containing the found gym or empty if not found.
     */
    Optional<Gym> findGymByIdAndOwnerId(Long gymId, Long ownerId);
}
