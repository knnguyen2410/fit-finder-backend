package com.example.fitfinder.repository;

import com.example.fitfinder.models.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {

    Optional<Gym> findById(Long gymId);

    Optional<Gym> findByName(String name);

    boolean existsByAddressStreet(String addressStreet);

    boolean existsByName(String name);

    Optional<Gym> findGymByIdAndOwnerId(Long gymId, Long ownerId);

    List<Gym> findByOwnerId(Long ownerId);

}
