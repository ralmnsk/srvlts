package com.facultative.service.validator;

import com.facultative.model.Person;


/**
 * The type Person validator.
 */
public class PersonValidator {

    public boolean validateLogin(String login) {
        String regex="[a-zA-Zа-яА-Я0-9]{3,30}";
        return login != null && login.matches(regex);
    }


    public boolean validatePassword(String password) {
        String regex="[a-zA-Zа-яА-Я0-9]{3,30}";
        return password !=null && password.matches(regex);
    }


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
    public boolean isValid(Person person) {
        if( person !=null){
            boolean isLoginNormal=validateLogin(person.getLogin());
            boolean isPasswordNormal=validatePassword(person.getPassword());
            boolean isSurnameNormal=validateSurname(person.getSurname());
            boolean isNameNormal=validateSurname(person.getName());

            return (isLoginNormal&&isPasswordNormal && isSurnameNormal&&isNameNormal);
        }
        return false;
    }
}
