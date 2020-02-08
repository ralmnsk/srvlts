package com.facultative.dao;


/**
 * The interface Dao person.
 *
 * @param <T> the type parameter
 */
public interface IDaoPerson<T> extends IDao<T> {
    /**
     * Gets by login.
     *
     * @param login the login
     * @return the by login
     */
    T getByLogin(String login);
}
