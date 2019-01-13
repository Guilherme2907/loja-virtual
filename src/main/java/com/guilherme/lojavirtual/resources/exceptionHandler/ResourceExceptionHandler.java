/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.resources.exceptionHandler;

import com.guilherme.lojavirtual.services.exception.DataIntegrityViolationExceptionCustom;
import com.guilherme.lojavirtual.services.exception.ObjectNotFoundErrorCustom;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Guilherme
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundErrorCustom.class)
    public ResponseEntity<StandartErrorMessage> getObjectNotFound(ObjectNotFoundErrorCustom exc) {
        StandartErrorMessage standartError = new StandartErrorMessage(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standartError);
    }

    @ExceptionHandler(DataIntegrityViolationExceptionCustom.class)
    public ResponseEntity<StandartErrorMessage> getDataIntegrityViolation(DataIntegrityViolationExceptionCustom e) {
        StandartErrorMessage standartError = new StandartErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standartError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorMessage> getMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationErrorMessage validationErrorMessage = new ValidationErrorMessage(HttpStatus.BAD_REQUEST.value(), "Erro na validação", System.currentTimeMillis());
        List<String> errors = new ArrayList<>();
        int count = 0;
        for (FieldError field : e.getBindingResult().getFieldErrors()) {
            errors.add(field.getDefaultMessage());
            validationErrorMessage.setErrors(field.getField(), errors);
            count++;
        }
        System.out.println(count);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorMessage);        
    }
}
