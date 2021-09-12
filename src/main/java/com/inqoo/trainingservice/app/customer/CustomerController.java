package com.inqoo.trainingservice.app.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    private List<Customer> getList() {
        log.info("Getting list of customers");
        return customerService.getAllCustomerList();
    }

    @PostMapping
    private Customer saveNew(@RequestBody Customer customer) {
        log.info("Saving new customer: " +customer.getName());
        return customerService.saveNewCustomer(customer);
    }
}