package com.example.fitfinder.security;

import com.example.fitfinder.models.Owner;
import com.example.fitfinder.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyOwnerDetailsService implements UserDetailsService {

    private OwnerService ownerService;

    @Autowired
    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * This is the method that comes from the UserDetailsService interface
     * loadUserByUsername returns an owner using their email address, or throws an exception if email address not found
     * @param email is used to find the owner
     * @return the owner
     * @throws UsernameNotFoundException if email is not found in owner repository
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Owner owner = ownerService.findOwnerByEmail(email);
        return new MyOwnerDetails(owner);
    }
}
