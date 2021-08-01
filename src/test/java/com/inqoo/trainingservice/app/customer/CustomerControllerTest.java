package com.inqoo.trainingservice.app.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private CustomerService customerService;

    ObjectMapper object = new ObjectMapper();

    @Test
    public void shouldAddACustomer() throws Exception {
        //given
        Customer customer = new Customer("Marcin", "505-009-546", "33-864-22-68", "mbutora@gmail.com",
                UUID.fromString("e7b3ccde-26ff-4df0-b571-04f473a3aa93"));
        String content = object.writeValueAsString(customer);
        //expect
        mvc.perform(
                post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.name").value(customer.getName()))
                .andExpect(jsonPath("$.mobileNumber").value(customer.getMobileNumber()))
                .andExpect(jsonPath("$.homeNumber").value(customer.getHomeNumber()))
                .andExpect(jsonPath("$.emailAddress").value(customer.getEmailAddress()))
                .andExpect(jsonPath("$.customerUUID").value(customer.getCustomerUUID().toString()));
    }
}