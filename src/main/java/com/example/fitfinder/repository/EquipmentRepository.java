package com.example.fitfinder.repository;

import com.example.fitfinder.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    boolean existsByNameAndBrand(String name, String brand);
}
