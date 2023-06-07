package com.example.fitfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="owners") // name of table in H2 database
public class Owner {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key
    private Long id;

    @Column
    private String name;

    @Column(unique = true) // each owner's email is unique
    private String email;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // owners can send password data to server, but it is not sent back for the owner to view in the JSON object
    private String password;

    @Column
    private String image;

    // one owner can have many gyms
    @OneToMany(mappedBy = "owner", orphanRemoval = true) // orphanRemoval removes the gyms from database if we deleted it from an owner
    @LazyCollection(LazyCollectionOption.FALSE) // all gyms will be eagerly loaded (gym data is retrieved together with owner data from the database)
    @JsonIgnoreProperties("owner") // excludes data from JSON object viewed by client
    private List<Gym> gymList;

    // no-args constructor
    public Owner() {}

    // parameterized constructor
    public Owner(Long id, String name, String email, String password, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // getters and setters for model relationships
    public List<Gym> getGymList() {
        return gymList;
    }

    public void setGymList(List<Gym> gymList) {
        this.gymList = gymList;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
