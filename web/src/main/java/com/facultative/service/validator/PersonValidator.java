package com.facultative.service.validator;

import com.facultative.model.Person;


/**
 * The type Person validator.
 */
public class PersonValidator {

    private boolean validateLogin(String login) {
        String regex="[a-zA-Zа-яА-Я]{3,30}";
        return login.matches(regex);
    }


    private boolean validatePassword(String password) {
        String regex="[a-zA-Zа-яА-Я0-9]{3,30}";
        return password.matches(regex);
    }


    private boolean validateSurname(String surname) {
        String regex="[a-zA-Zа-яА-Я-]{2,30}";
        return surname.matches(regex);
    }


    private boolean validateName(String name) {
        return validateSurname(name);
    }


    /**
     * Is valid boolean.
     *
     * @param person the person
     * @return the boolean
     */
    public boolean isValid(Person person) {
        boolean isLoginNormal=validateLogin(person.getLogin());
        boolean isPasswordNormal=validatePassword(person.getPassword());
        boolean isSurnameNormal=validateSurname(person.getSurname());
        boolean isNameNormal=validateName(person.getName());

        return (isLoginNormal&&isPasswordNormal && isSurnameNormal&&isNameNormal);
    }
}
