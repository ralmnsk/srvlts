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
     *              The method is used to get true/false of an login registration.
     *              True means registered. False means unregistered.
     * @return the boolean
     */
    boolean isRegistered(String login);

    /**
     * Register. This is a method to register a new person.
     *
     * @param person the person
     * @return the boolean
     */
    boolean register(Person person);
}
