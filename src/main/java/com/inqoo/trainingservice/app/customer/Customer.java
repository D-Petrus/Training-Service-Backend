package com.inqoo.trainingservice.app.customer;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(unique = true)
    private String mobileNumber;
    @Column(unique = true)
    private String homeNumber;
    @Column(unique = true)
    private String emailAddress;

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public Customer(String name, String mobileNumber, String homeNumber, String emailAddress) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.homeNumber = homeNumber;
        this.emailAddress = emailAddress;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
