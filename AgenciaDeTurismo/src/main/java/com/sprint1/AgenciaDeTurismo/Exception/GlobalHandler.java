package com.sprint1.AgenciaDeTurismo.Exception;

import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;
import com.sprint1.AgenciaDeTurismo.DTO.ValidationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(PaymentRequiredException.class)
    public ResponseEntity<ErrorDTO> handlerRuntime(PaymentRequiredException exception) {
        return new ResponseEntity<>(new ErrorDTO("Los siguientes errores fueron encontrados", List.of(exception.getMessage())), HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handlerRuntime(NotFoundException exception) {
        return new ResponseEntity<>(new ErrorDTO("Los siguientes errores fueron encontrados", List.of(exception.getMessage())), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDTO> handlerRuntime(BadRequestException exception) {
        return new ResponseEntity<>(new ErrorDTO("Los siguientes errores fueron encontrados", List.of(exception.getMessage())), HttpStatus.BAD_REQUEST);
    }

    // @validated
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationDTO> validationException(MethodArgumentNotValidException e){
        return ResponseEntity.ok(
                new ValidationDTO(
                        e.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList())
                )
        );
    }

    // @valid
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationDTO> validationException(ConstraintViolationException e){
        return ResponseEntity.ok(
                new ValidationDTO(
                        e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList())
                )
        );
    }

    // investigar de cambiar el metodo HttpMessageNotReadableException
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ValidationDTO> validationException( HttpMessageNotReadableException e){
        return ResponseEntity.status(400).body(
                new ValidationDTO(List.of("Formato de fecha debe ser yyyy-MM-dd" + e.getLocalizedMessage())
                )
        );
    }

}