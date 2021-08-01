package com.inqoo.trainingservice.app.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    private List<Customer> getList() {
        return customerService.getAllCustomerList();
    };

    @PostMapping
    private Customer saveNew(@RequestBody Customer customer) {
        return customerService.saveNewCustomer(customer);
    }
}