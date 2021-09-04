package com.inqoo.trainingservice.app.offer;

import com.inqoo.trainingservice.app.category.CategoryRepository;
import com.inqoo.trainingservice.app.course.Course;
import com.inqoo.trainingservice.app.course.CourseRepository;
import com.inqoo.trainingservice.app.customer.CustomerRepository;
import com.inqoo.trainingservice.app.email.EmailService;
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
    private final EmailService emailService;

    public OfferService(CategoryRepository categoryRepository,
                        SubcategoryRepository subcategoryRepository,
                        CourseRepository courseRepository,
                        CustomerRepository customerRepository,
                        OfferRepository offerRepository,
                        OfferConverter offerConverter, EmailService emailService) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.courseRepository = courseRepository;
        this.customerRepository = customerRepository;
        this.offerRepository = offerRepository;
        this.offerConverter = offerConverter;
        this.emailService = emailService;
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

    OfferDTO create(OfferDTO offerDTO) {;
        Offer offer = offerRepository.save(offerConverter.convertDTOToOffer(offerDTO));
        List<Course> courses = offer.getCourses();
        BigDecimal priceTotal = sumPriceOfOffer(courses);
        int durationTotal = sumDurationOfCourses(courses);
        offer.setSummaryPrice(priceTotal);
        offer.setSummaryDuration(durationTotal);
        offerRepository.save(offer);
        emailService.sendMail(offerDTO.getMail(),
                "Oferta szkolenia z obszaru ["+offerDTO.getCategoryName()+"]",
                "<html>" +
                        "<head>" +
                        "</head>" +
                        "<body>" +
                        "<h2>Dzień dobry</h2>" +
                        "<p>Przesyłamy ofertę Twoich kursów z kategorii: <b>" + offerDTO.getCategoryName() + "</b></p" +
                        ">" +
                        "<ul>" +
                        "<li>"+ String.join("</li><li>", offerDTO.getCourses()) + "</li>" +
                        "</ul>" +
                        "<p>Koszt: <b>" + offer.getSummaryPrice() +"</b></p>" +
                        "<p>Czas trwania: <b>" + offer.getSummaryDuration() +"</b></p>" +
                        "<br />" +
                        "<p>Pozdrawiamy,</p>" +
                        "<p><b>Zespół INQOO</b></p>" +
                        "</body>" +
                        "</html>",
                true);
        return offerConverter.convertOfferToDTO(offer);
    }
}