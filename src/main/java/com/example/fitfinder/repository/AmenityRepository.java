package com.example.fitfinder.repository;

import com.example.fitfinder.models.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    /**
     * Checks if an amenity exists with the given name.
     *
     * @param name The name of the amenity.
     * @return true if an amenity with the given name exists, false otherwise.
     */
    boolean existsByName(String name);

    /**
     * Finds an amenity by its ID and gym ID.
     *
     * @param amenityId The ID of the amenity.
     * @param gymId The ID of the gym.
     * @return An optional containing the found amenity or empty if not found.
     */
    Optional<Amenity> findAmenityByIdAndGymId(Long amenityId, Long gymId);
}
