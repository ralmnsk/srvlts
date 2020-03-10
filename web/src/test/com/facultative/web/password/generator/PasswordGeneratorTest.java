package com.facultative.web.password.generator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    @Test
    void createPassword() {
        String password = PasswordGenerator.createPassword("password");
        System.out.println(password+ " length "+ password.length());
        assertTrue(!password.isEmpty());
    }

    @Test
    void verifyPassword() {
        String passwordOneHash = PasswordGenerator.createPassword("password");
        String passwordTwo = "password";
        boolean isVerified = PasswordGenerator.verifyPassword(passwordTwo, passwordOneHash);
        assertTrue(isVerified);
    }

    @Test
    void verifyIncorrectPassword() {
        String passwordOneHash = PasswordGenerator.createPassword("password");
        String passwordTwo = "pass1word";
        boolean isVerified = PasswordGenerator.verifyPassword(passwordTwo, passwordOneHash);
        assertFalse(isVerified);
    }
}