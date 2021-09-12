package com.inqoo.trainingservice.app.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/order")
@Slf4j
class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    List<Order> getAllOrders() {
        log.info("Getting list of orders");
        return orderService.findAllOrder();
    }

    @PostMapping
    ResponseEntity<Order> saveNewOrder(@RequestBody Order order){
        Order saveNewOrder = orderService.saveNewJob(order);
        log.info("Saving new order: "+order.getOffer());
        return new ResponseEntity<>(saveNewOrder, HttpStatus.CREATED);
    }
}
