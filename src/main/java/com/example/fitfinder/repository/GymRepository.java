package com.example.fitfinder.repository;

import com.example.fitfinder.models.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym, Long> {

    Optional<Gym> findByName(String name);

    Optional<Gym> findGymByIdAndOwnerId(Long gymId, Long ownerId);
}
