package com.inqoo.trainingservice.app.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("/{offerId}/{firstName}/{lastName}/{start}/{end}")
    ResponseEntity<Order> saveNewOrder(@PathVariable Long offerId,
                                       @PathVariable String firstName,
                                       @PathVariable String lastName,
                                       @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                       @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
        Order saveNewOrder = orderService.createOrder(offerId, firstName,lastName, start, end);
        log.info("Saving new order: "+offerId);
        return new ResponseEntity<>(saveNewOrder, HttpStatus.CREATED);
    }
}
