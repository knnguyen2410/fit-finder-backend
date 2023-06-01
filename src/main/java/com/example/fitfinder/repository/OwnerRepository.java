package com.example.fitfinder.repository;

import com.example.fitfinder.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    // used in owner registration
    boolean existsByEmail(String email);

    // used in owner login
    Owner findOwnerByEmail(String email);
}
