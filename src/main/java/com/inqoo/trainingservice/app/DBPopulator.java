package com.inqoo.trainingservice.app;

import com.inqoo.trainingservice.app.category.Category;
import com.inqoo.trainingservice.app.category.CategoryService;
import com.inqoo.trainingservice.app.course.Course;
import com.inqoo.trainingservice.app.course.CourseService;
import com.inqoo.trainingservice.app.customer.Customer;
import com.inqoo.trainingservice.app.customer.CustomerService;
import com.inqoo.trainingservice.app.subcategory.Subcategory;
import com.inqoo.trainingservice.app.subcategory.SubcategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@Profile("prod")
class DBPopulator implements CommandLineRunner {

    private final CategoryService categoryService;
    private final SubcategoryService subcategoryService;
    private final CourseService courseService;
    private final CustomerService customerService;

    DBPopulator(CategoryService categoryService, SubcategoryService subcategoryService, CourseService courseService, CustomerService customerService) {
        this.categoryService = categoryService;
        this.subcategoryService = subcategoryService;
        this.courseService = courseService;
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) {
        Category it = new Category("IT", "Kursy IT", UUID.randomUUID());
        Category sport = new Category("Sport", "Kursy sportowe", UUID.randomUUID());
        Category medica = new Category("Medica", "Kursy Medyczne", UUID.randomUUID());

        Customer customer = new Customer("Marcin Butora", "505-009-546", "33-864-22-68", "mbutora@gmail.com", UUID.randomUUID());

        customerService.saveNewCustomer(customer);

        Subcategory beginners = new Subcategory("Dla początkujących", "Kursy dla początkujących", UUID.randomUUID());
        Subcategory advanced = new Subcategory("Dla zaawansowanych", "Kursy dla zaawansowanych", UUID.randomUUID());
        Subcategory FSD = new Subcategory("Fullstack Developer", "Kurs FSD", UUID.randomUUID());

        Course course1 = new Course("JavaScript Developer", "Zostań front-end developerem - twórz przyjazne i dynamiczne strony internetowe", 300, BigDecimal.valueOf(9500), UUID.randomUUID());
        Course course2 = new Course("Java Developer", "Zostań back-end developerem – programuj logikę, która stoi za dużymi i zaawansowanymi systemami webowymi.", 420, BigDecimal.valueOf(10900), UUID.randomUUID());
        Course course3 = new Course("Python Developer", "Zostań Back-end Developerem - programuj logikę, która stoi za stroną www lub aplikacją internetową.", 280, BigDecimal.valueOf(9000), UUID.randomUUID());
        Course course4 = new Course("Python - analiza danych", "Wykorzystaj programowanie do zautomatyzowania swojej codziennej pracy z dużymi bazami danych.", 120, BigDecimal.valueOf(4500), UUID.randomUUID());
        Course course5 = new Course("JS, React, Redux", "Zostań specjalistą JavaScript na kursie dla średniozaawansowanych", 130, BigDecimal.valueOf(4000), UUID.randomUUID());
        Course course6 = new Course("Docker - wstęp do konteneryzacji", "Zostań zauważony przez przyszłego pracodawcę dzięki znajomości Dockera.", 20, BigDecimal.valueOf(1990), UUID.randomUUID());
        Course course7 = new Course("Fullstack Developer by INQOO", "Zostań Full-Stack Developer z INQOO", 380, BigDecimal.valueOf(1000), UUID.randomUUID());

        categoryService.saveNewCategory(it);
        categoryService.saveNewCategory(sport);
        categoryService.saveNewCategory(medica);

        subcategoryService.saveNewSubcategory(beginners, it.getName());
        subcategoryService.saveNewSubcategory(advanced, it.getName());
        subcategoryService.saveNewSubcategory(FSD, it.getName());

        courseService.saveNewCourse(beginners.getName(), course1);
        courseService.saveNewCourse(beginners.getName(), course2);
        courseService.saveNewCourse(beginners.getName(), course3);
        courseService.saveNewCourse(advanced.getName(), course4);
        courseService.saveNewCourse(advanced.getName(), course5);
        courseService.saveNewCourse(advanced.getName(), course6);
        courseService.saveNewCourse(FSD.getName(), course7);
    }
}