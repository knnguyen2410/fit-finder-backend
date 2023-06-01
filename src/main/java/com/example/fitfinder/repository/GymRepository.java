package com.example.fitfinder.repository;

import com.example.fitfinder.models.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {
}
