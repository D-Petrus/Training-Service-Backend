package com.inqoo.trainingservice.app.exception;

import com.inqoo.trainingservice.app.category.CategoryNotFoundException;
import com.inqoo.trainingservice.app.course.CourseListEmptyException;
import com.inqoo.trainingservice.app.course.CourseNotFoundException;
import com.inqoo.trainingservice.app.customer.CustomerIsAlreadyExistsException;
import com.inqoo.trainingservice.app.customer.CustomerNotFoundException;
import com.inqoo.trainingservice.app.order.OrderForTrainerNotCreatedException;
import com.inqoo.trainingservice.app.subcategory.SubcategoryNotFoundException;
import com.inqoo.trainingservice.app.trainer.TrainerIsAlreadySavedException;
import com.inqoo.trainingservice.app.trainer.TrainerNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {NameAlreadyTakenException.class,
            TooLongDescriptionException.class,
            TrainerIsAlreadySavedException.class,
            OrderForTrainerNotCreatedException.class,
            CustomerIsAlreadyExistsException.class,
            CourseListEmptyException.class,
            EmailNotValidException.class,
            HomeNumberNotValidException.class,
            MobileNumberNotValidException.class})
    ResponseEntity<ApiExceptionDetails> handleBadRequestExceptions(RuntimeException e) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {TrainerNotFoundException.class,
            SubcategoryNotFoundException.class,
            CategoryNotFoundException.class,
            CustomerNotFoundException.class,
            CourseNotFoundException.class})
    ResponseEntity<ApiExceptionDetails> handleNotFoundExceptions(RuntimeException e) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.NOT_FOUND);
    }
}
