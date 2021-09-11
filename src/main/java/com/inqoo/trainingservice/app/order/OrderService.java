package com.inqoo.trainingservice.app.order;

import com.inqoo.trainingservice.app.exception.CouldNotCreateAnOrderForTrainerException;
import com.inqoo.trainingservice.app.offer.Offer;
import com.inqoo.trainingservice.app.offer.OfferRepository;
import com.inqoo.trainingservice.app.trainer.Trainer;
import com.inqoo.trainingservice.app.trainer.TrainerNotFoundException;
import com.inqoo.trainingservice.app.trainer.TrainerRepository;
import com.inqoo.trainingservice.app.absence.Absence;
import com.inqoo.trainingservice.app.absence.AbsenceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class OrderService {
    private final AbsenceRepository absenceRepository;
    private final OrderRepository orderRepository;
    private final OfferRepository offerRepository;
    private final TrainerRepository trainerRepository;

    public OrderService(AbsenceRepository unavailabilityRepository,
                        OrderRepository jobRepository,
                        OfferRepository offerRepository, TrainerRepository trainerRepository) {
        this.absenceRepository = unavailabilityRepository;
        this.orderRepository = jobRepository;
        this.offerRepository = offerRepository;
        this.trainerRepository = trainerRepository;
    }

    public Order saveNewJob(Order job) {
        Optional<Trainer> trainer = trainerRepository.findByFirstNameAndLastName(job.getTrainer().getFirstName(),
                job.getTrainer().getLastName());
        if (trainer.isPresent()) {
            List<LocalDate> dates = Stream.iterate(job.getStartCourse(), d -> d.plusDays(1))
                    .limit(ChronoUnit.DAYS.between(job.getStartCourse(), job.getEndCourse()) + 1)
                    .collect(toList());
            for (LocalDate date : dates) {
                Optional<Absence> absence = absenceRepository.checkAvailabilityForTrainer(date,
                        job.getTrainer().getFirstName(), job.getTrainer().getLastName());
                if (absence.isPresent()) {
                    throw new TrainerNotFoundException();
                }
            }
            Optional<Offer> offerForEmail = offerRepository.findByCustomerEmailAddress(job.getOffer().getCustomer().getEmailAddress());
            Order jobs = new Order(offerForEmail.get(), trainer.get(), job.getStartCourse(), job.getEndCourse());
            return orderRepository.save(jobs);
        }
        throw new CouldNotCreateAnOrderForTrainerException();
    }
}
