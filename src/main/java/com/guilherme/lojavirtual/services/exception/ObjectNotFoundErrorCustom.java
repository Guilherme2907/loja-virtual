/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.services.exception;

/**
 *
 * @author Guilherme
 */
public class ObjectNotFoundErrorCustom extends RuntimeException{

    public ObjectNotFoundErrorCustom(String message) {
        super(message);
    }

    public ObjectNotFoundErrorCustom(String message, Throwable cause) {
        super(message, cause);
    } 
}
