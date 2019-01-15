/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.resources.exceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public class ValidationErrorMessage extends StandartErrorMessage {

    List<FieldError> errors = new ArrayList();

    public ValidationErrorMessage(Integer status, String message, Long timeStamp) {
        super(status, message, timeStamp);
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(String field,String message) {
        errors.add(new FieldError(field,message));
    }
}
