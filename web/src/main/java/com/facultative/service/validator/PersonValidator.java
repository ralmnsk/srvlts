package com.facultative.service.validator;

import com.facultative.model.Person;


/**
 * The type Person validator.
 */
public class PersonValidator {

    /**
     * Validate login boolean.
     *
     * @param login the login
     * @return the boolean
     */
    public boolean validateLogin(String login) {
        String regex="[a-zA-Zа-яА-Я0-9]{3,30}";
        return login != null && login.matches(regex);
    }


    /**
     * Validate password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public boolean validatePassword(String password) {
        String regex="[a-zA-Zа-яА-Я0-9]{3,20}";
        return password !=null && password.matches(regex);
    }


    /**
     * Validate surname boolean.
     *
     * @param surname the surname
     * @return the boolean
     */
    public boolean validateSurname(String surname) {
        String regex="[a-zA-Zа-яА-Я-]{2,30}";
        return surname !=null && surname.matches(regex);
    }

    /**
     * Is valid boolean.
     *
     * @param person the person
     * @return the boolean
     */
    public boolean isValid(Person person,String password) {
        if( person !=null){
            boolean isLoginNormal=validateLogin(person.getLogin());
            boolean isPasswordNormal=validatePassword(password);
            boolean isSurnameNormal=validateSurname(person.getSurname());
            boolean isNameNormal=validateSurname(person.getName());

            return (isLoginNormal&&isPasswordNormal && isSurnameNormal&&isNameNormal);
        }
        return false;
    }
}
