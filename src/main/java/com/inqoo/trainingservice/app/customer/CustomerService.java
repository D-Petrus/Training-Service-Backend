package com.inqoo.trainingservice.app.customer;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private Pattern regexPatternMobile;
    private Pattern regexPatternHomeNumber;
    private Matcher regMatcher;

    public Customer saveNewCustomer(Customer customer) {
        if (!validateEmailAddress(customer.getEmailAddress())) {
            throw new EmailNotValidException("Mail not valid");
        };

        if (!validateHomeNumber(customer.getHomeNumber())) {
            throw new HomeNumberNotValidException("Home Number not valid");
        };

        if (!validateMobileNumber(customer.getMobileNumber())) {
            throw new MobileNumberNotValidException("Mobile number not valid");
        };

        return customerRepository.save(customer);
    }

    public boolean validateEmailAddress(String emailAddress) {

        boolean valid = EmailValidator.getInstance().isValid(emailAddress);
        if (valid) {
            return true;
        }
        return false;
    }

    public boolean validateMobileNumber(String mobileNumber) {
        regexPatternMobile = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{3}$");
        regMatcher = regexPatternMobile.matcher(mobileNumber);
        if (regMatcher.matches()) {
            return true;
        }
        return false;
    }

    public boolean validateHomeNumber(String mobileNumber) {
        regexPatternHomeNumber = Pattern.compile("[0-9]{2}-[0-9]{3}-[0-9]{2}-[0-9]{2}$");
        regMatcher = regexPatternHomeNumber.matcher(mobileNumber);
        if (regMatcher.matches()) {
            return true;
        }
        return false;
    }

}

