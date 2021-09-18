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

        Subcategory beginners = new Subcategory("Dla początkujących", "Kursy dla początkujących", UUID.randomUUID());
        Subcategory advanced = new Subcategory("Dla zaawansowanych", "Kursy dla zaawansowanych", UUID.randomUUID());
        Subcategory FSD = new Subcategory("Fullstack Developer", "Kurs FSD", UUID.randomUUID());
        Subcategory Football = new Subcategory("Piłka nożna", "Kurs piłki nożnej", UUID.randomUUID());
        Subcategory FirstHealth = new Subcategory("Pierwsza pomoc", "Kurs pierwszej pomocy", UUID.randomUUID());


        subcategoryService.saveNewSubcategory(beginners, it.getName());
        subcategoryService.saveNewSubcategory(advanced, it.getName());
        subcategoryService.saveNewSubcategory(FSD, it.getName());
        subcategoryService.saveNewSubcategory(Football, sport.getName());
        subcategoryService.saveNewSubcategory(FirstHealth, medica.getName());

        Course course1 = new Course("JavaScript Developer", "Zostań front-end developerem - twórz przyjazne i dynamiczne strony internetowe", 300, BigDecimal.valueOf(9500), UUID.randomUUID());
        Course course2 = new Course("Java Developer", "Zostań back-end developerem – programuj logikę, która stoi za dużymi i zaawansowanymi systemami webowymi.", 420, BigDecimal.valueOf(10900), UUID.randomUUID());
        Course course3 = new Course("Python Developer", "Zostań Back-end Developerem - programuj logikę, która stoi za stroną www lub aplikacją internetową.", 280, BigDecimal.valueOf(9000), UUID.randomUUID());
        Course course4 = new Course("Python - analiza danych", "Wykorzystaj programowanie do zautomatyzowania swojej codziennej pracy z dużymi bazami danych.", 120, BigDecimal.valueOf(4500), UUID.randomUUID());
        Course course5 = new Course("JS, React, Redux", "Zostań specjalistą JavaScript na kursie dla średniozaawansowanych", 130, BigDecimal.valueOf(4000), UUID.randomUUID());
        Course course6 = new Course("Docker - wstęp do konteneryzacji", "Zostań zauważony przez przyszłego pracodawcę dzięki znajomości Dockera.", 20, BigDecimal.valueOf(1990), UUID.randomUUID());
        Course course7 = new Course("Fullstack Developer by INQOO", "Zostań Full-Stack Developer z INQOO", 380, BigDecimal.valueOf(1000), UUID.randomUUID());
        Course course8 = new Course("Asystent trenera - dystans w 1x1", "Skracanie dystansu w sytuacji 1 × 1 w oparciu o kompleksową technikę interwencji 1 × 1 oraz orientację przestrzenną", 380, BigDecimal.valueOf(1000), UUID.randomUUID());
        Course course9 = new Course("Trener współczesny", "Zrozumieć ruch bez piłki w ataku", 380, BigDecimal.valueOf(1000), UUID.randomUUID());
        Course course10 = new Course("Instruktor Sportu - Piłka Nożna", "Kurs kończy się egzaminem zdawanym przed komisją CKKS", 380, BigDecimal.valueOf(1000), UUID.randomUUID());
        Course course11 = new Course("Kierownik drużyny z wsparciem technologicznym", "Kurs na kierownika drużyny z najnowszymi technologiami wykorzystywanymi w sporcie", 380, BigDecimal.valueOf(1000), UUID.randomUUID());
        Course course12 = new Course("Pierwsza pomoc przedmedyczna", "Podstawowe szkolenie z pierwszej pomocy dla firm i osób indywidualnych", 380, BigDecimal.valueOf(1000), UUID.randomUUID());
        Course course13 = new Course("Ratownictwo drogowe", "Szkolenie z pierwszej pomocy drogowej z ćwiczeniami praktycznymi", 380, BigDecimal.valueOf(1000), UUID.randomUUID());
        Course course14 = new Course("Kursy kwalifikacyjne dla pielęgniarek", "Kurs kwalifikacyjny pielęgniarstwa anestezjologicznego ma na celu przygotowanie pielęgniarki do pełnienia funkcji zawodowych pielęgniarki anestezjologicznej i intensywnej opieki;", 380, BigDecimal.valueOf(1000), UUID.randomUUID());
        Course course15 = new Course("Kurs – rejestratorka medyczna", "Celem kursu jest zdobycie wiedzy i umiejętności do pracy w sekretariacie medycznym.", 380, BigDecimal.valueOf(1000), UUID.randomUUID());

        courseService.saveNewCourse(beginners.getName(), course1);
        courseService.saveNewCourse(beginners.getName(), course2);
        courseService.saveNewCourse(beginners.getName(), course3);
        courseService.saveNewCourse(advanced.getName(), course4);
        courseService.saveNewCourse(advanced.getName(), course5);
        courseService.saveNewCourse(advanced.getName(), course6);
        courseService.saveNewCourse(FSD.getName(), course7);
        courseService.saveNewCourse(Football.getName(), course8);
        courseService.saveNewCourse(Football.getName(), course9);
        courseService.saveNewCourse(Football.getName(), course10);
        courseService.saveNewCourse(Football.getName(), course11);
        courseService.saveNewCourse(FirstHealth.getName(), course12);
        courseService.saveNewCourse(FirstHealth.getName(), course13);
        courseService.saveNewCourse(FirstHealth.getName(), course14);
        courseService.saveNewCourse(FirstHealth.getName(), course15);
    }
}