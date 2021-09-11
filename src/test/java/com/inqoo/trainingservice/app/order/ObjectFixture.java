package com.inqoo.trainingservice.app.order;

import com.inqoo.trainingservice.app.absence.AbsenceService;
import com.inqoo.trainingservice.app.category.Category;
import com.inqoo.trainingservice.app.category.CategoryService;
import com.inqoo.trainingservice.app.course.Course;
import com.inqoo.trainingservice.app.course.CourseService;
import com.inqoo.trainingservice.app.customer.Customer;
import com.inqoo.trainingservice.app.customer.CustomerService;
import com.inqoo.trainingservice.app.offer.Offer;
import com.inqoo.trainingservice.app.offer.OfferConverter;
import com.inqoo.trainingservice.app.offer.OfferService;
import com.inqoo.trainingservice.app.subcategory.Subcategory;
import com.inqoo.trainingservice.app.subcategory.SubcategoryService;
import com.inqoo.trainingservice.app.trainer.Trainer;
import com.inqoo.trainingservice.app.trainer.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class ObjectFixture {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubcategoryService subcategoryService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private OfferConverter converter;
    @Autowired
    private OrderService jobService;
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private AbsenceService absenceService;
    @Autowired
    private OrderFacade orderFacade;

    public Category newCategory(String name, String description, UUID randomUUID) {
        Category category = new Category(name, description, randomUUID);
        categoryService.saveNewCategory(category);
        return category;
    }

    public Subcategory newSubcategory(String name, String description, UUID randomUUID, String categoryName) {
        Subcategory subcategory = new Subcategory(name, description, randomUUID);
        subcategoryService.saveNewSubcategory(subcategory, categoryName);
        return subcategory;
    }

    public Course newCourse(String name, String description, int duration, BigDecimal price, UUID randomUUID,
                             String subcategoryName) {
        Course course = new Course(name, description, duration, price, randomUUID);
        courseService.saveNewCourse(subcategoryName, course);
        return course;
    }

    public Customer newCustomer(String name, String mobileNumber, String homeNumber, String emailAddress,
                                 UUID randomUUID) {
        Customer customer = new Customer(name, mobileNumber, homeNumber, emailAddress, randomUUID);
        customerService.saveNewCustomer(customer);
        return customer;
    }

    public Offer newOffer(Category category, Subcategory subcategory, List<Course> courses, Customer customer) {
        Offer offer = new Offer(category, subcategory, courses, customer);
        offerService.create(converter.convertOfferToDTO(offer));
        return offer;
    }

    public Trainer newTrainer(String firstName, String lastName, String experience, Long phoneNumber,
                               String emailAddress) {
        Trainer trainer = new Trainer(firstName, lastName, experience, phoneNumber, emailAddress);
        trainerService.saveNewTrainer(trainer);
        return trainer;
    }

    public Order newJob(Offer offer, Trainer trainer, LocalDate startCourse, LocalDate endCourse) {
        Order job = new Order(offer, trainer, startCourse, endCourse);
        jobService.saveNewJob(job);
        return job;
    }

    public AbsenceProjection newAbsenceProjection(Trainer trainer, LocalDate startAbsence, LocalDate endAbsence) {
        AbsenceProjection absenceProjection = new AbsenceProjection(trainer, startAbsence, endAbsence);
        orderFacade.createNew(absenceProjection.getTrainer().getFirstName(), absenceProjection.getTrainer().getLastName(), absenceProjection.getStartAbsence(), absenceProjection.getEndDate());
        return absenceProjection;
    }
}
