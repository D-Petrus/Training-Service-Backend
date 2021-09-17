package com.inqoo.trainingservice.app.order;

import com.inqoo.trainingservice.app.offer.Offer;
import com.inqoo.trainingservice.app.offer.OfferRepository;
import com.inqoo.trainingservice.app.trainer.Trainer;
import com.inqoo.trainingservice.app.trainer.TrainerRepository;

public class OrderConverter {
    private final OfferRepository offerRepository;
    private final TrainerRepository trainerRepository;

    public OrderConverter(OfferRepository offerRepository, TrainerRepository trainerRepository) {
        this.offerRepository = offerRepository;
        this.trainerRepository = trainerRepository;
    }

    public OrderDTO convertOrderToDTO(Order order) {
        Offer offer = order.getOffer();
        Trainer trainer = order.getTrainer();
        return new OrderDTO();
    }


}
