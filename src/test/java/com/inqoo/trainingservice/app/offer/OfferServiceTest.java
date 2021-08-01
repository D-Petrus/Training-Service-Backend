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

    @Test
    public void shouldAddAnOffer() {
        //given
        Category category = new Category("Java", "Kursy Java", UUID.randomUUID());
        Category savedCategory = categoryService.saveNewCategory(category);
        Subcategory subcategory = new Subcategory("Testy", "Testy jednostkowe", UUID.randomUUID());
        Subcategory savedSubcategory = subcategoryService.saveNewSubcategory(subcategory, savedCategory.getName());
        Course course = new Course("Testy jednostkowe", "kursy o testach", 120L,
                BigDecimal.valueOf(1200), UUID.randomUUID());
        Course savedCourse = courseService.saveNewCourse(savedSubcategory.getName(), course);
        Customer customer = new Customer(1,  "Marcin", "505-009-546", "23-222-22-22", "mbutora@gmail.com",
                UUID.randomUUID());
        Customer savedCustomer = customerService.saveNewCustomer(customer);
        //when
        Offer savedOffer = offerService.save(savedCustomer.getId(), savedCategory.getId(), savedCategory.getId(), savedCourse.getId());

        //then
        assertThat(savedOffer).isNotNull();
    }
}