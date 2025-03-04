package com.tp.taskmanager.task_manager.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(),"Resource Not Found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidAuthException.class)
    public ResponseEntity<?> invalidAuthException(ResourceNotFoundException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(),"You are not authorised");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<?> handleProductNotFoundException(ArrayIndexOutOfBoundsException exception){
        CustomErrorResponse productNotFound = new CustomErrorResponse(LocalDateTime.now(), exception.getMessage(), "Product Not Found");
        return new ResponseEntity<>(productNotFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex){
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                LocalDateTime.now(),
                errorMessage,
                "Validation failed"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }
}
