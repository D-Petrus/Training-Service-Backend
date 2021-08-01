package com.inqoo.trainingservice.app.customer;

import javax.persistence.Column;
import java.util.Objects;
import java.util.UUID;

public class CustomerDTO {
    private final String name;
    private final String mobileNumber;
    private final String homeNumber;
    private final String emailAddress;
    private final UUID customerUUID;

    public CustomerDTO(String name, String mobileNumber, String homeNumber, String emailAddress, UUID customerUUID) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.homeNumber = homeNumber;
        this.emailAddress = emailAddress;
        this.customerUUID = customerUUID;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public UUID getCustomerUUID() {
        return customerUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(mobileNumber, that.mobileNumber) && Objects.equals(homeNumber, that.homeNumber) && Objects.equals(emailAddress, that.emailAddress) && Objects.equals(customerUUID, that.customerUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mobileNumber, homeNumber, emailAddress, customerUUID);
    }
}
