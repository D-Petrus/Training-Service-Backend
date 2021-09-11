package com.inqoo.trainingservice.app.job;

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
public class JobService {
    private final AbsenceRepository absenceRepository;
    private final JobRepository jobRepository;
    private final OfferRepository offerRepository;
    private final TrainerRepository trainerRepository;

    public JobService(AbsenceRepository unavailabilityRepository,
                      JobRepository jobRepository,
                      OfferRepository offerRepository, TrainerRepository trainerRepository) {
        this.absenceRepository = unavailabilityRepository;
        this.jobRepository = jobRepository;
        this.offerRepository = offerRepository;
        this.trainerRepository = trainerRepository;
    }

    public Job saveNewJob(Job job) {
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
            Job jobs = new Job(offerForEmail.get(), trainer.get(), job.getStartCourse(), job.getEndCourse());
            return jobRepository.save(jobs);
        }
        throw new RuntimeException("Could not create a order for trainer!");
    }
}
