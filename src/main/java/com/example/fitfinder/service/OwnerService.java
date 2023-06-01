package com.example.fitfinder.service;

import com.example.fitfinder.models.Owner;
import com.example.fitfinder.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OwnerService {
    private OwnerRepository ownerRepository;

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
}
