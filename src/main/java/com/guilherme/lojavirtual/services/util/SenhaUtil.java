/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.lojavirtual.services.util;

import java.util.Random;

/**
 *
 * @author Guilherme
 */
public class SenhaUtil {

    public static Random random = new Random();

    public static String createNewPass() {
        char[] password = new char[10];
        for (int i = 0; i < password.length; i++) {
            password[i] = randomChar();
        }
        return password.toString();
    }

    public static char randomChar() {
        int num = random.nextInt(3);
        switch (num) {
            case 0:
                return (char) (random.nextInt(10) + 48);
            case 1:
                return (char) (random.nextInt(25) + 95);
            default:
                return (char) (random.nextInt(25) + 65);
        }
    }
}
