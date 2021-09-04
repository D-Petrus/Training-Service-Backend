package com.inqoo.trainingservice.app.offer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class OfferDTO {
    private String mail;
    private String subcategoryName;
    private String categoryName;
    private List<String> courses;
    private BigDecimal sumPrice;
    private int sumDuration;

    public OfferDTO(String mail,
                    String subcategoryName,
                    String categoryName,
                    List<String> coursesList,
                    BigDecimal sumPrice,
                    int sumDuration) {
        this.mail = mail;
        this.subcategoryName = subcategoryName;
        this.categoryName = categoryName;
        this.courses = coursesList;
        this.sumPrice = sumPrice;
        this.sumDuration = sumDuration;
    }

    @JsonCreator
    public OfferDTO(@JsonProperty("mail") String mail,
                    @JsonProperty("subcategoryName") String subcategoryName,
                    @JsonProperty("categoryName") String categoryName,
                    @JsonProperty("courses") List<String> coursesList) {
        this.mail = mail;
        this.subcategoryName = subcategoryName;
        this.categoryName = categoryName;
        this.courses = coursesList;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public int getSumDuration() {
        return sumDuration;
    }

    public void setSumDuration(int sumDuration) {
        this.sumDuration = sumDuration;
    }
}
