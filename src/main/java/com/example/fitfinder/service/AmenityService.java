package com.example.fitfinder.service;

import com.example.fitfinder.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmenityService {

    private AmenityRepository amenityRepository;

    @Autowired
    public void setAmenityRepository(AmenityRepository amenityRepository) {
        this.amenityRepository = amenityRepository;
    }
}
