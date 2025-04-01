package com.lambda_complex.generala.exceptions;

import com.lambda_complex.generala.dto.response.ErrorDto;
import com.lambda_complex.generala.dto.response.KeyValueDto;
import com.lambda_complex.generala.dto.response.ValidationErrorDto;
import org.springframework.dao.DataIntegrityViolationException;
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
    public ResponseEntity<ErrorDto<List<ValidationErrorDto>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<ValidationErrorDto> errorsList = new ArrayList<>();

        for (FieldError error : result.getFieldErrors()) {
            if (error.getDefaultMessage() != null) {
                errorsList.add(new ValidationErrorDto(
                        error.getField(),
                        error.getDefaultMessage(),
                        (String) error.getRejectedValue()
                ));
            }
        }

        return new ResponseEntity<>(
                new ErrorDto<>(
                        false,
                        "Validation Error",
                        errorsList
                ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDto<ValidationErrorDto>> handleEmailAlreadyExist(EmailAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorDto<>(
                false,
                ex.getMessage(),
                new ValidationErrorDto(
                        "email",
                        ex.getMessage(),
                        ex.getValue()
                )
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDto<String>> handleIntegrityViolations(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(new ErrorDto<>(
                false,
                "Integrity Violation",
                ex.getMessage()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorDto<KeyValueDto>> handlePlayerNotFound(PlayerNotFoundException ex) {
        return new ResponseEntity<>(new ErrorDto<>(
                false,
                "Player not found",
                new KeyValueDto(ex.getKey(), ex.getValue())
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlayersNotFoundException.class)
    public ResponseEntity<ErrorDto<List<KeyValueDto>>> handlePlayersNotFound(PlayersNotFoundException ex) {
        List<KeyValueDto> errorsList = new ArrayList<>();

        ex.getErrors().forEach(e -> errorsList.add(new KeyValueDto("email", e)));

        return new ResponseEntity<>(
                new ErrorDto<>(false, ex.getMessage(), errorsList),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<ErrorDto<KeyValueDto>> handleMatchNotFound(MatchNotFoundException ex) {
        return new ResponseEntity<>(new ErrorDto<>(
                false,
                "Match not found",
                new KeyValueDto(ex.getKey(), ex.getValue())
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OwnershipException.class)
    public ResponseEntity<ErrorDto<String>> handleNotAnOwner(OwnershipException ex) {
        return new ResponseEntity<>(new ErrorDto<>(
                false,
                "Action not allowed",
                ex.getMessage()
        ), HttpStatus.FORBIDDEN);
    }
}
