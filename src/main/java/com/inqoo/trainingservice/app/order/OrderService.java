package com.inqoo.trainingservice.app.order;

import com.inqoo.trainingservice.app.offer.Offer;
import com.inqoo.trainingservice.app.offer.OfferRepository;
import com.inqoo.trainingservice.app.trainer.Trainer;
import com.inqoo.trainingservice.app.trainer.TrainerNotFoundException;
import com.inqoo.trainingservice.app.trainer.TrainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class OrderService {
    private final AbsenceProjectionRepository absenceProjectionRepository;
    private final OrderRepository orderRepository;
    private final OfferRepository offerRepository;
    private final TrainerRepository trainerRepository;

    public OrderService(AbsenceProjectionRepository absenceProjectionRepository,
                        OrderRepository orderRepository,
                        OfferRepository offerRepository,
                        TrainerRepository trainerRepository) {
        this.absenceProjectionRepository = absenceProjectionRepository;
        this.orderRepository = orderRepository;
        this.offerRepository = offerRepository;
        this.trainerRepository = trainerRepository;
    }

//    public Order saveNewOrder(Order order) {
//        Optional<Trainer> trainer = trainerRepository.findByFirstNameAndLastName(order.getTrainer().getFirstName(),
//                order.getTrainer().getLastName());
//        if (trainer.isPresent()) {
//            List<LocalDate> dates = Stream.iterate(order.getStartCourse(), d -> d.plusDays(1))
//                    .limit(ChronoUnit.DAYS.between(order.getStartCourse(), order.getEndCourse()) + 1)
//                    .collect(toList());
//            for (LocalDate date : dates) {
//                Optional<AbsenceProjection> absence = absenceProjectionRepository.checkAvailabilityForTrainer(date,
//                        order.getTrainer().getFirstName(), order.getTrainer().getLastName());
//                if (absence.isPresent()) {
//                    throw new TrainerNotFoundException("Trainer not found!");
//                }
//            }
//            Optional<Offer> offerForEmail = offerRepository.findByCustomerEmailAddress(order.getOffer().getCustomer().getEmailAddress());
//            Order orders = new Order(offerForEmail.get(), trainer.get(), order.getStartCourse(), order.getEndCourse());
//            return orderRepository.save(orders);
//        }
//        throw new OrderForTrainerNotCreatedException("Could not create an order for trainer");
//    }

    public Order createOrder(Long offerId, String trainerFirstName, String trainerLastName, LocalDate start, LocalDate end) {
        Optional<Trainer> foundedTrainer = trainerRepository.findByFirstNameAndLastName(trainerFirstName, trainerLastName);
        Optional<Offer> foundedOffer = offerRepository.findById(offerId);
        if (foundedTrainer.isEmpty()) {
            throw new TrainerNotFoundException("Trainer not found");
        }
        if (foundedOffer.isPresent()) {
            Order order = new Order(foundedOffer.get(), foundedTrainer.get(), start, end);
            return orderRepository.save(order);
        }
        throw new OrderForTrainerNotCreatedException("Order not created!");
    }

    public List<Order> findAllOrder(){
        return orderRepository.findAll();
    }
}
