package com.facultative.service.registration;

import com.facultative.model.Person;

public interface IRegistration {
    boolean isRegistered(String login);
    void register(Person person);
}
