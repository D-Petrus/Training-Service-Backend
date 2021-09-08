package com.inqoo.trainingservice.app.job;

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
import com.inqoo.trainingservice.app.unavailability.Unavailability;
import com.inqoo.trainingservice.app.unavailability.UnavailabilityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JobServiceTest {
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
    private JobService jobService;
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private UnavailabilityService unavailabilityService;

    private Category newCategory(String name, String description, UUID randomUUID) {
        Category category = new Category(name, description, randomUUID);
        categoryService.saveNewCategory(category);
        return category;
    }

    private Subcategory newSubcategory(String name, String description, UUID randomUUID, String categoryName) {
        Subcategory subcategory = new Subcategory(name, description, randomUUID);
        subcategoryService.saveNewSubcategory(subcategory, categoryName);
        return subcategory;
    }

    private Course newCourse(String name, String description, int duration, BigDecimal price, UUID randomUUID,
                             String subcategoryName) {
        Course course = new Course(name, description, duration, price, randomUUID);
        courseService.saveNewCourse(subcategoryName, course);
        return course;
    }

    private Customer newCustomer(String name, String mobileNumber, String homeNumber, String emailAddress,
                                 UUID randomUUID) {
        Customer customer = new Customer(name, mobileNumber, homeNumber, emailAddress, randomUUID);
        customerService.saveNewCustomer(customer);
        return customer;
    }

    private Offer newOffer(Category category, Subcategory subcategory, List<Course> courses, Customer customer) {
        Offer offer = new Offer(category, subcategory, courses, customer);
        offerService.create(converter.convertOfferToDTO(offer));
        return offer;
    }

    private Trainer newTrainer(String firstName, String lastName, String experience, Long phoneNumber,
                               String emailAddress) {
        Trainer trainer = new Trainer(firstName, lastName, experience, phoneNumber, emailAddress);
        trainerService.saveNewTrainer(trainer);
        return trainer;
    }

    private Job newJob(Offer offer, Trainer trainer, LocalDate startCourse, LocalDate endCourse) {
        Job job = new Job(offer, trainer, startCourse, endCourse);
        jobService.saveNewJob(job);
        return job;
    }

    private Unavailability newUnavailability(Trainer trainer, LocalDate dayOfAbsence) {
        Unavailability unavailability = new Unavailability(trainer, dayOfAbsence);
        unavailabilityService.saveNewUnavailability(unavailability);
        return unavailability;
    }

    @Test
    public void shouldCreateAJobForTrainer() {
        //given
        Customer customer = newCustomer("Marcin Butora", "505-009-546", "22-322-22-22", "marcin@butora.pl",
                UUID.randomUUID());
        Category category = newCategory("IT", "Kursy IT", UUID.randomUUID());
        Subcategory subcategory = newSubcategory("Java", "Kursy Java", UUID.randomUUID(), category.getName());
        Course course = newCourse("Spring Kurs", "Kurs z wiedzy o Spring", 150, BigDecimal.valueOf(2000),
                UUID.randomUUID(), subcategory.getName());
        Offer offer = newOffer(category, subcategory, List.of(course), customer);
        Trainer trainer = newTrainer("Janek", "Kowalski", "hfhfhf", 324536424L, "janek@kowalski.pl");
        Unavailability unavailability = newUnavailability(trainer, LocalDate.of(2021,10,21));
        Job job = newJob(offer, trainer, LocalDate.of(2021,10,10), LocalDate.of(2021,10,20));

        //when
        Job savedJob = jobService.saveNewJob(job);

        //then
        assertThat(savedJob).isNotNull();
    }

}