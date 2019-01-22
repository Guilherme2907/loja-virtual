/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.resources.exceptionHandler;

import com.guilherme.lojavirtual.services.exception.AuthorizationException;
import com.guilherme.lojavirtual.services.exception.DataIntegrityViolationExceptionCustom;
import com.guilherme.lojavirtual.services.exception.ObjectNotFoundErrorCustom;
import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<StandartErrorMessage> getObjectNotFoundException(ObjectNotFoundErrorCustom e, HttpServletRequest req) {
        StandartErrorMessage standartError = new StandartErrorMessage(System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(), "Não encontrado", e.getMessage(), req.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standartError);
    }

    @ExceptionHandler(DataIntegrityViolationExceptionCustom.class)
    public ResponseEntity<StandartErrorMessage> getDataIntegrityViolationException(DataIntegrityViolationExceptionCustom e, HttpServletRequest req) {
        StandartErrorMessage standartError = new StandartErrorMessage(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Integridade de Dados", e.getMessage(), req.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standartError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorMessage> getMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest req) {
        ValidationErrorMessage validationErrorMessage = new ValidationErrorMessage(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Campos inválidos", e.getMessage(), req.getRequestURI());
        for (FieldError field : e.getBindingResult().getFieldErrors()) {
            validationErrorMessage.setErrors(field.getField(), field.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorMessage);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandartErrorMessage> getAuthorizationException(AuthorizationException e, HttpServletRequest req) {
        StandartErrorMessage standartError = new StandartErrorMessage(System.currentTimeMillis(),
                HttpStatus.FORBIDDEN.value(), "Integridade de Dados", e.getMessage(), req.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(standartError);
    }
}
