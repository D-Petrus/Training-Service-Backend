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
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    private ObjectFixture objectFixture;
    @Autowired
    private OrderService orderService;

    @Test
    public void shouldCreateAJobForTrainer() {
        //given
        Customer customer = objectFixture.newCustomer("Marcin Butora", "505-009-546", "22-322-22-22", "marcin@butora.pl",
                UUID.randomUUID());
        Category category = objectFixture.newCategory("IT", "Kursy IT", UUID.randomUUID());
        Subcategory subcategory = objectFixture.newSubcategory("Java", "Kursy Java", UUID.randomUUID(), category.getName());
        Course course = objectFixture.newCourse("Spring Kurs", "Kurs z wiedzy o Spring", 150, BigDecimal.valueOf(2000),
                UUID.randomUUID(), subcategory.getName());
        Offer offer = objectFixture.newOffer(category, subcategory, List.of(course), customer);
        Trainer trainer = objectFixture.newTrainer("Janek", "Kowalski", "hfhfhf", 324536424L, "janek@kowalski.pl");
        AbsenceProjection absenceProjection = objectFixture.newAbsenceProjection(trainer, LocalDate.of(2021, 10, 21), LocalDate.of(2021, 10, 22));
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
        AbsenceProjection absenceProjection = objectFixture.newAbsenceProjection(trainer, LocalDate.of(2021, 10, 10), LocalDate.of(2021, 10, 19));

        //then
        Assertions.assertThrows(TrainerNotFoundException.class, () -> objectFixture.newJob(offer, trainer, LocalDate.of(2021,10,10), LocalDate.of(2021,10,20)));
    }
}