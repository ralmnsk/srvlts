package com.facultative.service.validator;

import com.facultative.model.Person;

/**
 * The interface Validator.
 */
public interface IValidator {
    /**
     * Is valid boolean.
     *
     * @param person the person
     * @return the boolean
     */
    boolean isValid(Person person);
}
