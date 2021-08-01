package com.inqoo.trainingservice.app.customer;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String mobileNumber;
    @Column(unique = true, nullable = false)
    private String homeNumber;
    @Column(unique = true)
    private String emailAddress;
    @Column(unique = true)
    private UUID customerUUID;

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public Customer(long id, String name, String mobileNumber, String homeNumber, String emailAddress, UUID customerUUID) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.homeNumber = homeNumber;
        this.emailAddress = emailAddress;
        this.customerUUID = customerUUID;
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

    public UUID getCustomerUUID() {
        return customerUUID;
    }

    public void setCustomerUUID(UUID customerUUID) {
        this.customerUUID = customerUUID;
    }

    public long getId() {
        return id;
    }
}
