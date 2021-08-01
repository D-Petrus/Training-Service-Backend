package com.inqoo.trainingservice.app.customer;

public class CustomerConverter {
    public static CustomerDTO entityToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO(
                customer.getName(),
                customer.getMobileNumber(),
                customer.getHomeNumber(),
                customer.getEmailAddress(),
                customer.getCustomerUUID()

        );
        return customerDTO;
    }

    public static Customer dtoToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmailAddress(customerDTO.getEmailAddress());
        customer.setHomeNumber(customerDTO.getHomeNumber());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        customer.setCustomerUUID(customerDTO.getCustomerUUID());
        return customer;
    }

    ;

}
