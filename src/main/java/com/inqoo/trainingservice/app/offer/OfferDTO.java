package com.inqoo.trainingservice.app.offer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OfferDTO {
    private String mail;
    private String subcategoryName;
    private String categoryName;
    private List<String> coursesList = new ArrayList<>();
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
        this.coursesList = coursesList;
        this.sumPrice = sumPrice;
        this.sumDuration = sumDuration;
    }

    public OfferDTO(String mail,
                    String subcategoryName,
                    String categoryName,
                    List<String> coursesList) {
        this.mail = mail;
        this.subcategoryName = subcategoryName;
        this.categoryName = categoryName;
        this.coursesList = coursesList;
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

    public List<String> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<String> coursesList) {
        this.coursesList = coursesList;
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
