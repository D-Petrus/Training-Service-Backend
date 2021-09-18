package com.inqoo.trainingservice.app.order;

import com.inqoo.trainingservice.app.offer.Offer;
import com.inqoo.trainingservice.app.offer.OfferRepository;
import com.inqoo.trainingservice.app.trainer.Trainer;
import com.inqoo.trainingservice.app.trainer.TrainerNotFoundException;
import com.inqoo.trainingservice.app.trainer.TrainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
