package com.example.fitfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name ="amenities") // name of table in H2 database
public class Amenity {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key
    private Long id;

    @Column
    private String category;

    @Column
    private String subcategory;

    @Column
    private String name;

    @Column
    private String details;

    // many amenities can belong to one gym
    @ManyToOne
    @JoinColumn(name = "gym_id")
    @JsonIgnore // excludes data from JSON object viewed by client
    private Gym gym;

    // no-args constructor
    public Amenity() {}

    // parameterized constructor
    public Amenity(Long id, String category, String subcategory, String name, String details) {
        this.id = id;
        this.category = category;
        this.subcategory = subcategory;
        this.name = name;
        this.details = details;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    // getters and setters for model relationships
    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    @Override
    public String toString() {
        return "Amenity{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
