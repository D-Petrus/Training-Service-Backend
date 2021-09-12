package com.inqoo.trainingservice.app.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/order")
class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    List<Order> getAllOrders() {
        return orderService.findAllOrder();
    }

    @PostMapping
    ResponseEntity<Order> saveNewOrder(@RequestBody Order order){
        Order saveNewOrder = orderService.saveNewOrder(order);
        return new ResponseEntity<>(saveNewOrder, HttpStatus.CREATED);
    }
}
