package com.facultative.web.password.generator;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordGenerator {


    public static String createPassword(String password){
        return BCrypt.withDefaults().hashToString(6, password.toCharArray());
    }


    public static boolean verifyPassword(String pass, String bcryptHashString){
        return  BCrypt.verifyer().verify(pass.toCharArray(), bcryptHashString).verified;
    }
}
