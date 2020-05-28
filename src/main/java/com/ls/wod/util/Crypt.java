package com.ls.wod.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Crypt {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        String password = "123";
        String hash = "$2a$10$C.WDEu/vtprimBEb0madeep2zDx5XYCXwSTNBxJOj6PQbuGIVeIU6";
        System.out.println("Password: " + password);
        System.out.println("Crypt: " + encoder.encode(password));
        System.out.println("Crypt: " + encoder.matches(password, hash));
    }
}
