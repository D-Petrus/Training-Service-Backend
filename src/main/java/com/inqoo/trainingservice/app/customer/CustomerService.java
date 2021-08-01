package com.inqoo.trainingservice.app.customer;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private Pattern regexPatternMobile;
    private Pattern regexPatternHomeNumber;
    private Matcher regMatcher;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveNewCustomer(Customer customer) {
        if (customerRepository.findByEmailAddress(customer.getEmailAddress()).isPresent())
        {throw new CustomerIsAlreadyExistsException();
        };
        if (!validateEmailAddress(customer.getEmailAddress())) {
            throw new EmailNotValidException();
        };

        if (!validateHomeNumber(customer.getHomeNumber())) {
            throw new HomeNumberNotValidException();
        };

        if (!validateMobileNumber(customer.getMobileNumber())) {
            throw new MobileNumberNotValidException();
        };

        return customerRepository.save(customer);
    }

    private boolean validateEmailAddress(String emailAddress) {

        boolean valid = EmailValidator.getInstance().isValid(emailAddress);
        if (valid) {
            return true;
        }
        return false;
    }

    private boolean validateMobileNumber(String mobileNumber) {
        regexPatternMobile = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{3}$");
        regMatcher = regexPatternMobile.matcher(mobileNumber);
        if (regMatcher.matches()) {
            return true;
        }
        return false;
    }

    private boolean validateHomeNumber(String mobileNumber) {
        regexPatternHomeNumber = Pattern.compile("[0-9]{2}-[0-9]{3}-[0-9]{2}-[0-9]{2}$");
        regMatcher = regexPatternHomeNumber.matcher(mobileNumber);
        if (regMatcher.matches()) {
            return true;
        }
        return false;
    }

}

