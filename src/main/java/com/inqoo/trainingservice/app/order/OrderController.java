package com.inqoo.trainingservice.app.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    private List<Order> getAllOrders() { return orderService.getAll();}
    @PostMapping
    ResponseEntity<Order> saveNewOrder(@RequestBody Order order){
        Order saveNewOrder = orderService.saveNewJob(order);
        return new ResponseEntity<>(saveNewOrder, HttpStatus.CREATED);
    }
}
