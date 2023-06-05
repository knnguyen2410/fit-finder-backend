package com.example.fitfinder.security;

import com.example.fitfinder.models.Owner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class MyOwnerDetails implements UserDetails {

    // creates instance of owner
    // private means not accessible by other classes
    // final means owner properties cannot be changed
    private final Owner owner;

    // constructor
    public MyOwnerDetails(Owner owner){
        this.owner = owner;
    }

    // getter only, since we're not setting the owner
    public Owner getOwner(){
        return owner;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    public String getPassword() {
        return owner.getPassword();
    }

    @Override
    public String getUsername() {
        return owner.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
