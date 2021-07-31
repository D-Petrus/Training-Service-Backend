package com.inqoo.trainingservice.app.DTO;

import java.util.Objects;

public class TrainerDTO {
    private final String firstName;
    private final String lastName;
    private final String experience;


    public TrainerDTO(String firstName, String lastName, String experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getExperience() {
        return experience;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainerDTO that = (TrainerDTO) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(experience, that.experience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, experience);
    }
}
