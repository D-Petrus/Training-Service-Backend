package com.inqoo.trainingservice.app.service;


import com.inqoo.trainingservice.app.customer.*;
import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.trainer.Trainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CustomerServiceIT {
    @Autowired
    private CustomerService customerService;

    @Test
    public void shouldNotAddCustomerWhenInvalidEmailAddress() {
        //given
        Customer customer1 = new Customer(
                "Google",
                "500-500-500",
                "32-234-22-22",
                "test@gmailCom");

        //then
        Assertions.assertThrows(EmailNotValidException.class, () -> {
            customerService.saveNewCustomer(customer1);
        });
    }
    @Test
    public void shouldNotAddCustomerWhenInvalidMobile() {
        //given
        Customer customer1 = new Customer(
                "Google",
                "500-50-500",
                "32-346-22-22",
                "test@gmail.com");

        //then
        Assertions.assertThrows(MobileNumberNotValidException.class, () -> {
            customerService.saveNewCustomer(customer1);
        });
    }
    @Test
    public void shouldNotAddCustomerWhenInvalidHomeNumber() {
        //given
        Customer customer1 = new Customer(
                "Google",
                "500-500-500",
                "3-346-22-22",
                "test@gmail.com");

        //then
        Assertions.assertThrows(HomeNumberNotValidException.class, () -> {
            customerService.saveNewCustomer(customer1);
        });
    }
    @Test
    public void shouldAddCustomerWhenEmailAddressExist(){
        //given
        Customer customer1 = new Customer(
                "Google",
                "500-500-500",
                "32-346-22-22",
                "test@gmail.com");

        //then
        final Customer saveNewCustomer = customerService.saveNewCustomer(customer1);
        assertThat(saveNewCustomer).isEqualTo(customer1);
    }
    @Test
    public void shouldNotAddCustomerWhenEmailIsExist() {
        //given
        Customer customer1 = new Customer(
                "Google",
                "500-500-500",
                "32-346-22-22",
                "test@gmail.com");
        Customer customer2 = new Customer(
                "Google2",
                "500-500-600",
                "32-346-22-62",
                "test@gmail.com");

        //then
        customerService.saveNewCustomer(customer1);
        Assertions.assertThrows(CustomerIsAlreadyExistsException.class, () -> {
            customerService.saveNewCustomer(customer2);
        });
    }

}