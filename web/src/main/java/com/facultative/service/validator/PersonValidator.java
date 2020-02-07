package com.facultative.service.validator;

import com.facultative.model.Person;
import com.facultative.model.UserType;

public class PersonValidator implements IValidator {

    public boolean validateLogin(String login) {
        String regex="[a-zA-Zа-яА-Я]{3,20}";
        if(login.matches(regex)){
            return true;
        }
        return false;
    }


    public boolean validatePassword(String password) {
        String regex="[a-zA-Zа-яА-Я0-9]{3,20}";
        if(password.matches(regex)){
            return true;
        }
        return false;
    }


    public boolean validateSurname(String surname) {
        String regex="[a-zA-Zа-яА-Я-]{3,20}";
        if(surname.matches(regex)){
            return true;
        }
        return false;
    }


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

    public static void main(String[] args){
        PersonValidator validator=new PersonValidator();
        Person person=new Person();
        person.setLogin("qqq");
        person.setPassword("qqq");
        person.setSurname("qqq");
        person.setName("qqq");
        person.setRole(UserType.STUDENT);
        System.out.println(validator.isValid(person));
    }
}
