package com.inqoo.trainingservice.app.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trainer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    @Column(length = 4000)
    private String experience;
    @Column(unique = true)
    private Long phoneNumber;
    @Column(unique = true)
    private String emailAddress;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Trainer> trainersList = new ArrayList<>();

    public Trainer(String firstName, String lastName, String experience, Long phoneNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public Trainer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Trainer> getTrainersList() {
        return trainersList;
    }
}
