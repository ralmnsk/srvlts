package com.facultative.service.registration;

import com.facultative.model.Person;

/**
 * The interface Registration.
 */
public interface IRegistration {
    /**
     * Is registered boolean.
     *
     * @param login the login
     * @return the boolean
     */
    boolean isRegistered(String login);

    /**
     * Register.
     *
     * @param person the person
     * @return the boolean
     */
    boolean register(Person person);
}
