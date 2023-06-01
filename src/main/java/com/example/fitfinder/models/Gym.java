package com.example.fitfinder.models;

import javax.persistence.*;

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
    private String type;

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

    // no-args constructor
    public Gym() {}

    // parameterized constructor
    public Gym(Long id, String name, String type, String addressStreet, String addressCity, String addressState, Long addressZip, String hours, String phone, String details) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "Gym{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
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
