package com.inqoo.trainingservice.app.trainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class TrainerControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private TrainerService trainerService;

    ObjectMapper object = new ObjectMapper();

    @Test
    public void shouldAddCustomer() throws Exception{
        //given
        Trainer trainer = new Trainer(
                "Damian","Petrus","JAVA",
                505-505-505L,"damianpetrus@gmail.com");
        String content = object.writeValueAsString(trainer);
        //expect
        mvc.perform(
                post("/trainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.firstName").value(trainer.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(trainer.getLastName()))
                .andExpect(jsonPath("$.experience").value(trainer.getExperience()))
                .andExpect(jsonPath("$.phoneNumber").value(trainer.getPhoneNumber()))
                .andExpect(jsonPath("$.emailAddress").value(trainer.getEmailAddress()));
    }
}