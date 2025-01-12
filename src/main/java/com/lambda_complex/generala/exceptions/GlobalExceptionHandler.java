package com.lambda_complex.generala.exceptions;

import com.lambda_complex.generala.dto.response.ValidationErrorDto;
import com.lambda_complex.generala.enums.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDto> handleValidationExceptions(MethodArgumentNotValidException ex){
        BindingResult result = ex.getBindingResult();
        List<String> errorsList = new ArrayList<>();

        for (FieldError error : result.getFieldErrors()) {
            if (error.getDefaultMessage() != null){
                errorsList.add(error.getField() + ": " + error.getDefaultMessage());
            }
        }

        return new ResponseEntity<>(
                new ValidationErrorDto(
                        false,
                        Status.ERROR,
                        "Validation Error",
                        errorsList
                ), HttpStatus.BAD_REQUEST);
    }
}
