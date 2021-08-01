package com.inqoo.trainingservice.app.trainer;

import com.inqoo.trainingservice.app.trainer.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TrainerRepository extends JpaRepository<Trainer,Long> {
    Optional<Trainer> findByFirstName(String firstName);
    Optional<Trainer> findByLastName(String lastName);
    Optional<Trainer> findByFirstNameAndLastName(String firstName,String lastName);

}
