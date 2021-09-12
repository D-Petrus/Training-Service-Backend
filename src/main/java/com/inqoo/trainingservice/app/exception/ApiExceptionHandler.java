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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NameAlreadyTakenException.class)
    ResponseEntity<ApiExceptionDetails> handleNameAlreadyTakenException(NameAlreadyTakenException exception) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TooLongDescriptionException.class)
    ResponseEntity<ApiExceptionDetails> handleTooLongDescriptionException(TooLongDescriptionException exception) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TrainerNotFoundException.class)
    ResponseEntity<ApiExceptionDetails> handleTrainerNotFoundException(TrainerNotFoundException exception) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrainerIsAlreadySavedException.class)
    ResponseEntity<ApiExceptionDetails> handleTrainerIsAlreadySavedException(TrainerIsAlreadySavedException exception) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SubcategoryNotFoundException.class)
    ResponseEntity<ApiExceptionDetails> handleSubcategoryNotFoundException(SubcategoryNotFoundException exception) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    ResponseEntity<ApiExceptionDetails> handleCategoryNotFoundException(CategoryNotFoundException exception) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderForTrainerNotCreatedException.class)
    ResponseEntity<ApiExceptionDetails> handleOrderForTrainerNotCreated(OrderForTrainerNotCreatedException exception) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerIsAlreadyExistsException.class)
    ResponseEntity<ApiExceptionDetails> handleCustomerIsAlreadyExistsException(CustomerIsAlreadyExistsException exception) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    ResponseEntity<ApiExceptionDetails> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseListEmptyException.class)
    ResponseEntity<ApiExceptionDetails> handleCourseListEmptyException(CourseListEmptyException exception) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    ResponseEntity<ApiExceptionDetails> handleCourseNotFoundException(CourseNotFoundException exception) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailNotValidException.class)
    ResponseEntity<ApiExceptionDetails> handleEmailNotValidException(EmailNotValidException exception) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HomeNumberNotValidException.class)
    ResponseEntity<ApiExceptionDetails> handleHomeNumberNotValidException(HomeNumberNotValidException exception) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MobileNumberNotValidException.class)
    ResponseEntity<ApiExceptionDetails> handleMobileNumberNotValidException(MobileNumberNotValidException exception) {
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(apiExceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
