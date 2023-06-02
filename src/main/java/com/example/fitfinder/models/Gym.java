package com.example.fitfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="gyms") // name of table in H2 database
public class Gym {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key
    private Long id;

    @Column
    private String name;

    @Column
    private String category;

    @Column
    private String addressStreet;

    @Column
    private String addressCity;

    @Column
    private String addressState;

    @Column
    private Long addressZip;

    @Column
    private String hours;

    @Column
    private String phone;

    @Column
    private String details;

    // many gyms can belong to one owner
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore // excludes data from JSON object viewed by client
    private Owner owner;

    // one gym can have many pieces of equipment
    @OneToMany(mappedBy = "gym", orphanRemoval = true) // orphanRemoval removes the equipment from database if we deleted it from a gym
    @LazyCollection(LazyCollectionOption.FALSE) // all equipment data will be eagerly loaded (equipment data is retrieved together with gym data from the database)
    private List<Equipment> equipmentList;

    // one gym can have many amenities
    @OneToMany(mappedBy = "gym", orphanRemoval = true) // orphanRemoval removes the amenity from database if we deleted it from a gym
    @LazyCollection(LazyCollectionOption.FALSE) // all amenity data will be eagerly loaded (amenity data is retrieved together with gym data from the database)
    private List<Amenity> amenityList;

    // no-args constructor
    public Gym() {}

    // parameterized constructor
    public Gym(Long id, String name, String category, String addressStreet, String addressCity, String addressState, Long addressZip, String hours, String phone, String details) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZip = addressZip;
        this.hours = hours;
        this.phone = phone;
        this.details = details;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String type) {
        this.category = category;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public Long getAddressZip() {
        return addressZip;
    }

    public void setAddressZip(Long addressZip) {
        this.addressZip = addressZip;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    // getters and setters for model relationships
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<Amenity> getAmenityList() {
        return amenityList;
    }

    public void setAmenityList(List<Amenity> amenityList) {
        this.amenityList = amenityList;
    }

    @Override
    public String toString() {
        return "Gym{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", addressStreet='" + addressStreet + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", addressState='" + addressState + '\'' +
                ", addressZip=" + addressZip +
                ", hours='" + hours + '\'' +
                ", phone='" + phone + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
