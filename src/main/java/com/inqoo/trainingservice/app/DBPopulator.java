package com.inqoo.trainingservice.app;

import com.inqoo.trainingservice.app.category.Category;
import com.inqoo.trainingservice.app.category.CategoryService;
import com.inqoo.trainingservice.app.course.Course;
import com.inqoo.trainingservice.app.course.CourseService;
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

    DBPopulator(CategoryService categoryService, SubcategoryService subcategoryService, CourseService courseService) {
        this.categoryService = categoryService;
        this.subcategoryService = subcategoryService;
        this.courseService = courseService;
    }

    @Override
    public void run(String... args) {
        Category it = new Category("IT", "Kursy IT", UUID.randomUUID());
        Category sport = new Category("Sport", "Kursy sportowe", UUID.randomUUID());
        Category medica = new Category("Medica", "Kursy Medyczne", UUID.randomUUID());

        categoryService.saveNewCategory(it);
        categoryService.saveNewCategory(sport);
        categoryService.saveNewCategory(medica);

        Subcategory java = new Subcategory("Java", "Kursy Java", UUID.randomUUID());
        Subcategory spring = new Subcategory("Spring", "Kursy Spring", UUID.randomUUID());
        Subcategory hibernate = new Subcategory("Hibernate", "Kursy Hibernate", UUID.randomUUID());
        Subcategory fh = new Subcategory("Pierwsza pomoc", "Kurs Pierwszej Pomocy", UUID.randomUUID());
        Subcategory football = new Subcategory("Piłka Nożna", "Kursy odnośnie Piłki Nożnej", UUID.randomUUID());

        subcategoryService.saveNewSubcategory(java, it.getName());
        subcategoryService.saveNewSubcategory(spring, it.getName());
        subcategoryService.saveNewSubcategory(hibernate, it.getName());
        subcategoryService.saveNewSubcategory(fh, medica.getName());
        subcategoryService.saveNewSubcategory(football, sport.getName());

        Course course1 = new Course("Kurs 1", "Opis Kursu 1", 300, BigDecimal.valueOf(2500), UUID.randomUUID());
        Course course2 = new Course("Kurs 2", "Opis Kursu 2", 300, BigDecimal.valueOf(2500), UUID.randomUUID());
        Course course3 = new Course("Kurs 3", "Opis Kursu 3", 300, BigDecimal.valueOf(2500), UUID.randomUUID());
        Course course4 = new Course("Kurs 4", "Opis Kursu 4", 300, BigDecimal.valueOf(2500), UUID.randomUUID());
        Course course5 = new Course("Kurs 5", "Opis Kursu 5", 300, BigDecimal.valueOf(2500), UUID.randomUUID());
        courseService.saveNewCourse(java.getName(), course1);
        courseService.saveNewCourse(java.getName(), course2);
        courseService.saveNewCourse(spring.getName(), course3);
        courseService.saveNewCourse(sport.getName(), course4);
        courseService.saveNewCourse(medica.getName(), course5);
    }

}
