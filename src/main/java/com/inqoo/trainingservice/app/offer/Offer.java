package com.inqoo.trainingservice.app.offer;

import com.inqoo.trainingservice.app.category.Category;
import com.inqoo.trainingservice.app.course.Course;
import com.inqoo.trainingservice.app.customer.Customer;
import com.inqoo.trainingservice.app.subcategory.Subcategory;

import javax.persistence.*;
import java.util.List;

@Entity
class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private final Category category;
    @OneToOne
    private final Subcategory subcategory;
    @OneToMany
    private final List<Course> courseList;
    @OneToOne
    private final Customer customer;

    public Offer(Category category, Subcategory subcategory, List<Course> courseList, Customer customer) {
        this.category = category;
        this.subcategory = subcategory;
        this.courseList = courseList;
        this.customer = customer;
    }

    public Category getCategory() {
        return category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public Customer getCustomer() {
        return customer;
    }
}
