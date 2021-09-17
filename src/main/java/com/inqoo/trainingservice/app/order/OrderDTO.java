package com.inqoo.trainingservice.app.order;

import java.time.LocalDate;

public class OrderDTO {
    private Long offerId;
    private Long trainerId;
    private LocalDate startOrder;
    private LocalDate endOrder;

    public OrderDTO(Long offerId, Long trainerId, LocalDate startOrder, LocalDate endOrder) {
        this.offerId = offerId;
        this.trainerId = trainerId;
        this.startOrder = startOrder;
        this.endOrder = endOrder;
    }

    public OrderDTO() {
    }

    public Long getOfferId() {
        return offerId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public LocalDate getStartOrder() {
        return startOrder;
    }

    public LocalDate getEndOrder() {
        return endOrder;
    }
}
