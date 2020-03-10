package com.facultative.web.password.generator;

import at.favre.lib.crypto.bcrypt.BCrypt;


/**
 * The type BCrypt Password generator.
 */
public class PasswordGenerator {


    /**
     * Create password string.
     *
     * @param password the password of String type
     * @return the password hash of string type
     */
    public static String createPassword(String password){
        return BCrypt.withDefaults().hashToString(6, password.toCharArray());
    }


    /**
     * Verify password boolean.
     *
     * @param pass             the password
     * @param bcryptHashString the bcrypt hash string to compare with password hash string
     * @return the boolean
     */
    public static boolean verifyPassword(String pass, String bcryptHashString){
        return  BCrypt.verifyer().verify(pass.toCharArray(), bcryptHashString).verified;
    }
}
