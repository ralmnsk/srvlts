package com.facultative.service.validator;

import com.facultative.model.Person;
import com.facultative.model.UserType;

/**
 * The type Person validator.
 */
public class PersonValidator implements IValidator {

    /**
     * Validate login boolean.
     *
     * @param login the login
     * @return the boolean
     */
    public boolean validateLogin(String login) {
        String regex="[a-zA-Zа-яА-Я]{3,30}";
        if(login.matches(regex)){
            return true;
        }
        return false;
    }


    /**
     * Validate password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public boolean validatePassword(String password) {
        String regex="[a-zA-Zа-яА-Я0-9]{3,30}";
        if(password.matches(regex)){
            return true;
        }
        return false;
    }


    /**
     * Validate surname boolean.
     *
     * @param surname the surname
     * @return the boolean
     */
    public boolean validateSurname(String surname) {
        String regex="[a-zA-Zа-яА-Я-]{2,30}";
        if(surname.matches(regex)){
            return true;
        }
        return false;
    }


    /**
     * Validate name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean validateName(String name) {
        return validateSurname(name);
    }

    @Override
    public boolean isValid(Person person) {
        boolean isLoginNormal=validateLogin(person.getLogin());
        boolean isPasswordNormal=validatePassword(person.getPassword());
        boolean isSurnameNormal=validateSurname(person.getSurname());
        boolean isNameNormal=validateName(person.getName());
        if(isLoginNormal&&isPasswordNormal&&isSurnameNormal&&isNameNormal){
            return true;
        }
        return false;
    }
}
