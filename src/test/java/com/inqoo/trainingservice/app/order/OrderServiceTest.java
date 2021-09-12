package com.inqoo.trainingservice.app.order;

import com.inqoo.trainingservice.app.category.Category;
import com.inqoo.trainingservice.app.course.Course;
import com.inqoo.trainingservice.app.customer.Customer;
import com.inqoo.trainingservice.app.trainer.TrainerNotFoundException;
import com.inqoo.trainingservice.app.offer.Offer;
import com.inqoo.trainingservice.app.subcategory.Subcategory;
import com.inqoo.trainingservice.app.trainer.Trainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class OrderServiceTest {
    @Autowired
    private ObjectFixture objectFixture;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateOrderForTrainer() {
        //given
        Customer customer = objectFixture.newCustomer("Marcin Butora", "505-009-546", "22-322-22-22", "marcin@butora.pl",
                UUID.randomUUID());
        Category category = objectFixture.newCategory("IT", "Kursy IT", UUID.randomUUID());
        Subcategory subcategory = objectFixture.newSubcategory("Java", "Kursy Java", UUID.randomUUID(), category.getName());
        Course course = objectFixture.newCourse("Spring Kurs", "Kurs z wiedzy o Spring", 150, BigDecimal.valueOf(2000),
                UUID.randomUUID(), subcategory.getName());
        Offer offer = objectFixture.newOffer(category, subcategory, List.of(course), customer);
        Trainer trainer = objectFixture.newTrainer("Janek", "Kowalski", "hfhfhf", 324536424L, "janek@kowalski.pl");
        objectFixture.newAbsenceProjection(trainer, LocalDate.of(2021, 10, 21), LocalDate.of(2021, 10, 22));
        Order job = objectFixture.newJob(offer, trainer, LocalDate.of(2021,10,10), LocalDate.of(2021,10,20));

        //then
        assertThat(job).isNotNull();
    }
    @Test
    public void shouldThrowNoTrainerException() {
        //given
        Customer customer = objectFixture.newCustomer("Marcin Butora", "505-009-546", "22-322-22-22", "marcin@butora.pl",
                UUID.randomUUID());
        Category category = objectFixture.newCategory("IT", "Kursy IT", UUID.randomUUID());
        Subcategory subcategory = objectFixture.newSubcategory("Java", "Kursy Java", UUID.randomUUID(), category.getName());
        Course course = objectFixture.newCourse("Spring Kurs", "Kurs z wiedzy o Spring", 150, BigDecimal.valueOf(2000),
                UUID.randomUUID(), subcategory.getName());
        Offer offer = objectFixture.newOffer(category, subcategory, List.of(course), customer);
        Trainer trainer = objectFixture.newTrainer("Janek", "Kowalski", "hfhfhf", 324536424L, "janek@kowalski.pl");
        objectFixture.newAbsenceProjection(trainer, LocalDate.of(2021, 10, 10), LocalDate.of(2021, 10, 19));

        //then
        Assertions.assertThrows(TrainerNotFoundException.class, () -> objectFixture.newJob(offer, trainer, LocalDate.of(2021,10,10), LocalDate.of(2021,10,20)));
    }
    @Test
    public void orderControllerCheck() throws Exception {
        //given
        Customer customer = objectFixture.newCustomer("Marcin Butora", "505-009-546", "22-322-22-22", "marcin@butora.pl",
                UUID.randomUUID());
        Category category = objectFixture.newCategory("IT", "Kursy IT", UUID.randomUUID());
        Subcategory subcategory = objectFixture.newSubcategory("Java", "Kursy Java", UUID.randomUUID(), category.getName());
        Course course = objectFixture.newCourse("Spring Kurs", "Kurs z wiedzy o Spring", 150, BigDecimal.valueOf(2000),
                UUID.randomUUID(), subcategory.getName());
        objectFixture.newOffer(category, subcategory, List.of(course), customer);
        Trainer trainer = objectFixture.newTrainer("Janek", "Kowalski", "hfhfhf", 324536424L, "janek@kowalski.pl");
        objectFixture.newAbsenceProjection(trainer, LocalDate.of(2021, 10, 10), LocalDate.of(2021, 10, 19));

        //then
        this.mockMvc.perform(get("/order"))
                .andExpect(status().isOk());
    }
}