package com.example.fitfinder.models;

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
    private Long quantity;

    @Column
    private String details;

    @Column
    private String image;

    // many amenities can belong to one gym
    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    // no-args constructor
    public Amenity() {}

    // parameterized constructor
    public Amenity(Long id, String category, String subcategory, String name, Long quantity, String details, String image) {
        this.id = id;
        this.category = category;
        this.subcategory = subcategory;
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
        return "Amenity{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", details='" + details + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
