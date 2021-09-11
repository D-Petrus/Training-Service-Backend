package com.inqoo.trainingservice.app.service;


import com.inqoo.trainingservice.app.customer.*;
import com.inqoo.trainingservice.app.customer.EmailNotValidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CustomerServiceIT {
    @Autowired
    private CustomerService customerService;

    @Test
    public void shouldNotAddCustomerWhenInvalidEmailAddress() {
        //given
        CustomerDTO customer1 = new CustomerDTO(
                "Google",
                "500-500-500",
                "32-234-22-22",
                "test@gmailCom", UUID.randomUUID());

        //then
        Assertions.assertThrows(EmailNotValidException.class, () -> {
            customerService.saveNewCustomer(CustomerConverter.dtoToEntity(customer1));
        });
    }
    @Test
    public void shouldNotAddCustomerWhenInvalidMobile() {
        //given
        CustomerDTO customer1 = new CustomerDTO(
                "Google",
                "500-50-500",
                "32-346-22-22",
                "test@gmail.com", UUID.randomUUID());

        //then
        Assertions.assertThrows(MobileNumberNotValidException.class, () -> {
            customerService.saveNewCustomer(CustomerConverter.dtoToEntity(customer1));
        });
    }
    @Test
    public void shouldNotAddCustomerWhenInvalidHomeNumber() {
        //given
        CustomerDTO customer1 = new CustomerDTO(
                "Google",
                "500-500-500",
                "3-346-22-22",
                "test@gmail.com", UUID.randomUUID());

        //then
        Assertions.assertThrows(HomeNumberNotValidException.class, () -> {
            customerService.saveNewCustomer(CustomerConverter.dtoToEntity(customer1));
        });
    }
    @Test
    public void shouldAddCustomerWhenEmailAddressExist(){
        //given
        CustomerDTO customer1 = new CustomerDTO(
                "Google",
                "500-500-500",
                "32-346-22-22",
                "test@gmail.com", UUID.randomUUID());

        //then
        final Customer saveNewCustomer = customerService.saveNewCustomer(CustomerConverter.dtoToEntity(customer1));
        assertThat(saveNewCustomer.getName()).isEqualTo(customer1.getName());
    }
    @Test
    public void shouldNotAddCustomerWhenEmailIsExist() {
        //given
        CustomerDTO customer1 = new CustomerDTO(
                "Google",
                "500-500-500",
                "32-346-22-22",
                "test@gmail.com", UUID.randomUUID());
        CustomerDTO customer2 = new CustomerDTO(
                "Google2",
                "500-500-600",
                "32-346-22-62",
                "test@gmail.com", UUID.randomUUID());

        //then
        customerService.saveNewCustomer(CustomerConverter.dtoToEntity(customer1));
        Assertions.assertThrows(CustomerIsAlreadyExistsException.class, () -> {
            customerService.saveNewCustomer(CustomerConverter.dtoToEntity(customer2));
        });
    }

}