package com.example.fitfinder.repository;

import com.example.fitfinder.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    /**
     * Checks if equipment exists with the given name and brand.
     *
     * @param name  The name of the equipment.
     * @param brand The brand of the equipment.
     * @return true if equipment with the given name and brand exists, false otherwise.
     */
    boolean existsByNameAndBrand(String name, String brand);

    /**
     * Finds equipment by its ID and gym ID.
     *
     * @param equipmentId The ID of the equipment.
     * @param gymId The ID of the gym.
     * @return An optional containing the found equipment or empty if not found.
     */
    Optional<Equipment> findEquipmentByIdAndGymId(Long equipmentId, Long gymId);
}
