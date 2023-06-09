package com.example.fitfinder.repository;

import com.example.fitfinder.models.Amenity;
import com.example.fitfinder.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    boolean existsByName(String name);

    Optional<Amenity> findAmenityByIdAndGymId(Long amenityId, Long GymId);

}
