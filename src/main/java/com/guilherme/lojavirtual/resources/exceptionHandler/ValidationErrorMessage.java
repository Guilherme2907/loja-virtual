/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.resources.exceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Guilherme
 */
public class ValidationErrorMessage extends StandartErrorMessage {

    private Map<String, List<String>> errors = new HashMap();

    public ValidationErrorMessage(Integer status, String message, Long timeStamp) {
        super(status, message, timeStamp);
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(String field,List<String> messages) {
        errors.put(field, messages);
    } 
}
