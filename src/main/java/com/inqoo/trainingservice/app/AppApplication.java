package com.inqoo.trainingservice.app;

import com.inqoo.trainingservice.app.category.Category;
import com.inqoo.trainingservice.app.category.CategoryService;
import com.inqoo.trainingservice.app.course.Course;
import com.inqoo.trainingservice.app.course.CourseService;
import com.inqoo.trainingservice.app.subcategory.Subcategory;
import com.inqoo.trainingservice.app.subcategory.SubcategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	private final CategoryService categoryService;
	private final SubcategoryService subcategoryService;
	private final CourseService courseService;

	public AppApplication(CategoryService categoryService, SubcategoryService subcategoryService, CourseService courseService) {
		this.categoryService = categoryService;
		this.subcategoryService = subcategoryService;
		this.courseService = courseService;
	}

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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

		Course javaCourse = new Course("Kurs Java dla początkująych", "Kurs organizowany przez INQOO dla początkujących", 300, BigDecimal.valueOf(2500), UUID.randomUUID());
		courseService.saveNewCourse(java.getName(), javaCourse);
	}
}
