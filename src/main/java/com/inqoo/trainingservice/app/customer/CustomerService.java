package com.inqoo.trainingservice.app.customer;

import com.inqoo.trainingservice.app.exception.EmailNotValidException;
import com.inqoo.trainingservice.app.exception.HomeNumberNotValidException;
import com.inqoo.trainingservice.app.exception.MobileNumberNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private Pattern regexPatternMobile;
    private Pattern regexPatternHomeNumber;
    private Matcher regMatcher;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveNewCustomer(Customer customer) {
        if (customerRepository.findByEmailAddress(customer.getEmailAddress()).isPresent()) {
            throw new CustomerIsAlreadyExistsException("Customer is already exist!");
        }

        if (!validateEmailAddress(customer.getEmailAddress())) {
            throw new EmailNotValidException("Email is not valid!");
        }

        if (!validateHomeNumber(customer.getHomeNumber())) {
            throw new HomeNumberNotValidException("Home number is not valid!");
        }

        if (!validateMobileNumber(customer.getMobileNumber())) {
            throw new MobileNumberNotValidException("Mobile number is not valid!");
        }

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomerList() {
        return customerRepository.findAll();
    }

    private boolean validateEmailAddress(String emailAddress) {
        return EmailValidator.getInstance().isValid(emailAddress);
    }

    private boolean validateMobileNumber(String mobileNumber) {
        regexPatternMobile = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{3}$");
        regMatcher = regexPatternMobile.matcher(mobileNumber);
        return regMatcher.matches();
    }

    private boolean validateHomeNumber(String homeNumber) {
        regexPatternHomeNumber = Pattern.compile("[0-9]{2}-[0-9]{3}-[0-9]{2}-[0-9]{2}$");
        regMatcher = regexPatternHomeNumber.matcher(homeNumber);
        return regMatcher.matches();
    }

}

