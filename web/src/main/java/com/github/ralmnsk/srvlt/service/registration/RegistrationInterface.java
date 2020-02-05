package com.github.ralmnsk.srvlt.service.registration;

import com.github.ralmnsk.srvlt.model.Person;
import com.github.ralmnsk.srvlt.model.UserType;

public interface RegistrationInterface {
    boolean isRegistered(String login);
    void register(Person person);
}
