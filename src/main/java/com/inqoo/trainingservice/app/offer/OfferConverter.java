package com.inqoo.trainingservice.app.offer;

import com.inqoo.trainingservice.app.category.Category;
import com.inqoo.trainingservice.app.category.CategoryNotFoundException;
import com.inqoo.trainingservice.app.category.CategoryRepository;
import com.inqoo.trainingservice.app.course.Course;
import com.inqoo.trainingservice.app.course.CourseListEmptyException;
import com.inqoo.trainingservice.app.course.CourseRepository;
import com.inqoo.trainingservice.app.customer.Customer;
import com.inqoo.trainingservice.app.customer.CustomerNotFoundException;
import com.inqoo.trainingservice.app.customer.CustomerRepository;
import com.inqoo.trainingservice.app.subcategory.Subcategory;
import com.inqoo.trainingservice.app.subcategory.SubcategoryNotFoundException;
import com.inqoo.trainingservice.app.subcategory.SubcategoryRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class OfferConverter {

    private final CustomerRepository customerRepository;
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final CourseRepository courseRepository;

    public OfferConverter(CustomerRepository customerRepository,
                          CategoryRepository categoryRepository,
                          SubcategoryRepository subcategoryRepository,
                          CourseRepository courseRepository) {
        this.customerRepository = customerRepository;
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.courseRepository = courseRepository;
    }

    public OfferDTO convertOfferToDTO(Offer offer) {
        Customer customer = offer.getCustomer();
        Category category = offer.getCategory();
        Subcategory subcategory = offer.getSubcategory();
        List<Course> courses = offer.getCourses();
        return new OfferDTO(
                customer.getEmailAddress(),
                subcategory.getName(),
                category.getName(),
                courses.stream()
                        .map(Course::getName)
                        .collect(toList()),
                offer.getSummaryPrice(),
                offer.getSummaryDuration()
        );
    }

    public Offer convertDTOToOffer(OfferDTO offerDTO) {
        Customer customer =
                customerRepository.findByEmailAddress(offerDTO.getMail())
                        .orElseThrow(CustomerNotFoundException::new);
        Category category =
                categoryRepository.findByName(offerDTO.getCategoryName())
                        .orElseThrow(CategoryNotFoundException::new);
        Subcategory subcategory =
                subcategoryRepository.findByName(offerDTO.getSubcategoryName())
                        .orElseThrow(SubcategoryNotFoundException::new);
        List<Course> courses = findAllCoursesWithNames(offerDTO.getCourses());
        BigDecimal summaryPrice = offerDTO.getSumPrice();
        int summaryDuration = offerDTO.getSumDuration();
        return new Offer(
                category,
                subcategory,
                courses,
                customer,
                summaryPrice,
                summaryDuration
        );
    }

    private List<Course> findAllCoursesWithNames(List<String> coursesNames) {
        List<Course> courseList = courseRepository.findByNameIn(coursesNames);
        if (courseList.isEmpty()) {
            throw new CourseListEmptyException();
        }
        return courseList;
    }
}
