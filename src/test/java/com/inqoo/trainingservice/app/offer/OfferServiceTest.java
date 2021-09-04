package com.inqoo.trainingservice.app.offer;

import com.inqoo.trainingservice.app.category.Category;
import com.inqoo.trainingservice.app.category.CategoryService;
import com.inqoo.trainingservice.app.course.Course;
import com.inqoo.trainingservice.app.course.CourseService;
import com.inqoo.trainingservice.app.customer.Customer;
import com.inqoo.trainingservice.app.customer.CustomerService;
import com.inqoo.trainingservice.app.subcategory.Subcategory;
import com.inqoo.trainingservice.app.subcategory.SubcategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class OfferServiceTest {
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

    @Test
    public void shouldAddAnOffer() {
        // given
        Customer customer = newCustomer("Marcin Butora", "505-009-546", "22-322-22-22", "marcin@butora.pl",
                UUID.randomUUID());
        Category category = newCategory("IT", "Kursy IT", UUID.randomUUID());
        Subcategory subcategory = newSubcategory("Java", "Kursy Java", UUID.randomUUID(), category.getName());
        Course course = newCourse("Spring Kurs", "Kurs z wiedzy o Spring", 150, BigDecimal.valueOf(2000),
                UUID.randomUUID(), subcategory.getName());
        Course course1 = newCourse("Hibernate", "Kurs z wiedzy o Hibernate", 140, BigDecimal.valueOf(2000),
                UUID.randomUUID(), subcategory.getName());
        List<String> coursesNames = List.of(course.getName(), course1.getName());
        OfferDTO offer = new OfferDTO(
                customer.getEmailAddress(),
                subcategory.getName(),
                category.getName(),
                coursesNames
        );

        // when
        offerService.create(offer);

        //then
        List<OfferDTO> allOffers = offerService.getAll();
        OfferDTO offerSavedInDB = allOffers.get(0);
        assertThat(allOffers.size()).isEqualTo(1);
        assertThat(offerSavedInDB.getMail()).isEqualTo(customer.getEmailAddress());
        assertThat(offerSavedInDB.getCategoryName()).isEqualTo(category.getName());
        assertThat(offerSavedInDB.getSubcategoryName()).isEqualTo(subcategory.getName());
        assertThat(offerSavedInDB.getCourses()).containsAll(coursesNames);
        assertThat(offerSavedInDB.getSumPrice()).isEqualTo(BigDecimal.valueOf(4000));
        assertThat(offerSavedInDB.getSumDuration()).isEqualTo(290);
    }
}