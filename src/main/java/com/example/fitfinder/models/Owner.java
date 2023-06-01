package com.example.fitfinder.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

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

    // no-args constructor
    public Owner() {}

    // parameterized constructor
    public Owner(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
