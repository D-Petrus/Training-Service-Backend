package com.inqoo.trainingservice.app.offer;

import com.inqoo.trainingservice.app.category.Category;
import com.inqoo.trainingservice.app.course.Course;
import com.inqoo.trainingservice.app.customer.Customer;
import com.inqoo.trainingservice.app.subcategory.Subcategory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Category category;
    @OneToOne
    private Subcategory subcategory;
    @OneToMany
    private List<Course> course;
    @OneToOne
    private Customer customer;
    private BigDecimal summaryPrice;
    private int summaryDuration;

    public Offer(Category category,
                 Subcategory subcategory,
                 List<Course> courseList,
                 Customer customer,
                 BigDecimal summaryPrice,
                 int summaryDuration) {
        this.category = category;
        this.subcategory = subcategory;
        this.course = courseList;
        this.customer = customer;
        this.summaryPrice = summaryPrice;
        this.summaryDuration = summaryDuration;
    }

    public Offer(Category category, Subcategory subcategory, List<Course> course, Customer customer) {
        this.category = category;
        this.subcategory = subcategory;
        this.course = course;
        this.customer = customer;
    }

    public Offer() {
    }

    public Category getCategory() {
        return category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public List<Course> getCourse() {
        return course;
    }

    public Customer getCustomer() {
        return customer;
    }

    public BigDecimal getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(BigDecimal summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public int getSummaryDuration() {
        return summaryDuration;
    }

    public void setSummaryDuration(int summaryDuration) {
        this.summaryDuration = summaryDuration;
    }
}
