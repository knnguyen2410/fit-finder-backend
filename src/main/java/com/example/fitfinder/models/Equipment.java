package com.example.fitfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name ="equipment") // name of table in H2 database
public class Equipment {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key
    private Long id;

    @Column
    private String category;

    @Column
    private String brand;

    @Column
    private String name;

    @Column
    private Long quantity;

    @Column
    private String details;

    @Column
    private String image;

    // many pieces of equipment can belong to one gym
    @ManyToOne
    @JoinColumn(name = "gym_id")
//    @JsonIgnore // excludes data from JSON object viewed by client
    private Gym gym;

    // no-args constructor
    public Equipment() {}

    // parameterized constructor
    public Equipment(Long id, String category, String brand, String name, Long quantity, String details, String image) {
        this.id = id;
        this.category = category;
        this.brand = brand;
        this.name = name;
        this.quantity = quantity;
        this.details = details;
        this.image = image;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        return "Equipment{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", details='" + details + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
