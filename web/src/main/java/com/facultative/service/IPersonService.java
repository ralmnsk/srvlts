package com.facultative.service;

import com.facultative.dao.IDao;

/**
 * The interface Person service.
 *
 * @param <T> the type parameter
 */
public interface IPersonService<T> extends IService<T> {
    /**
     * Gets by login.
     *
     * @param login the login
     * @return the by login
     */
    T getByLogin(String login);
}
