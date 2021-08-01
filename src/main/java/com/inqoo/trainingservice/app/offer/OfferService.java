package com.inqoo.trainingservice.app.offer;

import com.inqoo.trainingservice.app.category.Category;
import com.inqoo.trainingservice.app.category.CategoryNotFoundException;
import com.inqoo.trainingservice.app.category.CategoryRepository;
import com.inqoo.trainingservice.app.course.Course;
import com.inqoo.trainingservice.app.course.CourseNotFoundException;
import com.inqoo.trainingservice.app.course.CourseRepository;
import com.inqoo.trainingservice.app.customer.Customer;
import com.inqoo.trainingservice.app.customer.CustomerNotFoundException;
import com.inqoo.trainingservice.app.customer.CustomerRepository;
import com.inqoo.trainingservice.app.subcategory.Subcategory;
import com.inqoo.trainingservice.app.subcategory.SubcategoryNotFoundException;
import com.inqoo.trainingservice.app.subcategory.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class OfferService {
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final CourseRepository courseRepository;
    private final CustomerRepository customerRepository;
    private final OfferRepository offerRepository;

    public OfferService(CategoryRepository categoryRepository,
                        SubcategoryRepository subcategoryRepository,
                        CourseRepository courseRepository,
                        CustomerRepository customerRepository, OfferRepository offerRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.courseRepository = courseRepository;
        this.customerRepository = customerRepository;
        this.offerRepository = offerRepository;
    }

    public Offer save(Long customerId, Long catId, Long subCatId, Long courseId) {
        Optional<Customer> foundedCustomer = customerRepository.findById(customerId);
        if (foundedCustomer.isEmpty()) {
            throw new CustomerNotFoundException();
        }
        Optional<Category> foundedCat = categoryRepository.findById(catId);
        if (foundedCat.isEmpty()) {
            throw new CategoryNotFoundException("Category Not Found");
        }
        Optional<Subcategory> foundedSubCat = subcategoryRepository.findById(subCatId);
        if (foundedSubCat.isEmpty()) {
            throw new SubcategoryNotFoundException("Subcategory Not Found");
        }
        Optional<Course> foundedCourse = courseRepository.findById(courseId);
        if (foundedCourse.isEmpty()) {
            throw new CourseNotFoundException("Course Not Found");
        }
        return offerRepository.save(new Offer(foundedCat.get(), foundedSubCat.get(),
                List.of(courseRepository.getById(courseId)), foundedCustomer.get()));
    }

    public List<Offer> getAll() {
        return offerRepository.findAll();
    }
}
