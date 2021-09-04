package com.inqoo.trainingservice.app.offer;

import com.inqoo.trainingservice.app.category.CategoryRepository;
import com.inqoo.trainingservice.app.course.Course;
import com.inqoo.trainingservice.app.course.CourseRepository;
import com.inqoo.trainingservice.app.customer.CustomerRepository;
import com.inqoo.trainingservice.app.subcategory.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
class OfferService {
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final CourseRepository courseRepository;
    private final CustomerRepository customerRepository;
    private final OfferRepository offerRepository;
    private final OfferConverter offerConverter;

    public OfferService(CategoryRepository categoryRepository,
                        SubcategoryRepository subcategoryRepository,
                        CourseRepository courseRepository,
                        CustomerRepository customerRepository,
                        OfferRepository offerRepository,
                        OfferConverter offerConverter) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.courseRepository = courseRepository;
        this.customerRepository = customerRepository;
        this.offerRepository = offerRepository;
        this.offerConverter = offerConverter;
    }



    public List<OfferDTO> getAll() {
        return offerRepository.findAll().stream()
                .map(offerConverter::convertOfferToDTO)
                .collect(Collectors.toList());
    }

    public List<OfferDTO> getAllByMail(String mail) {
        return offerRepository.findByCustomerEmailAddress(mail)
                .stream()
                .map(offerConverter::convertOfferToDTO)
                .collect(Collectors.toList());
    }

    private BigDecimal sumPriceOfOffer(List<Course> courses) {
        return courses.stream()
                .map(Course::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private int sumDurationOfCourses(List<Course> courses) {
        return courses.stream()
                .mapToInt(Course::getDuration)
                .sum();
    }

    OfferDTO create(OfferDTO offerDTO) {
        Offer offer = offerRepository.save(offerConverter.convertDTOToOffer(offerDTO));
        List<Course> courses = offer.getCourses();
        BigDecimal priceTotal = sumPriceOfOffer(courses);
        int durationTotal = sumDurationOfCourses(courses);
        offer.setSummaryPrice(priceTotal);
        offer.setSummaryDuration(durationTotal);
        offerRepository.save(offer);
        return offerConverter.convertOfferToDTO(offer);
    }
}