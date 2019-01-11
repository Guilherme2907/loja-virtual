/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.resources.exceptionHandler;

import com.guilherme.lojavirtual.services.exception.ObjectNotFoundErrorCustom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Guilherme
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundErrorCustom.class)
    public ResponseEntity<StandartError> getStandartError(ObjectNotFoundErrorCustom exc) {
        StandartError standartError = new StandartError(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standartError);
    }

}