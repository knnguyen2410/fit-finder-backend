package com.example.fitfinder.repository;

import com.example.fitfinder.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    // used in owner registration
    /**
     * Checks if an owner exists with the given email.
     *
     * @param email The email of the owner.
     * @return true if an owner with the given email exists, false otherwise.
     */
    boolean existsByEmail(String email);

    // used in owner login
    /**
     * Finds an owner by their email.
     *
     * @param email The email of the owner.
     * @return The owner object corresponding to the given email.
     */
    Owner findOwnerByEmail(String email);
}