package com.facultative.dao;

import java.sql.SQLException;


/**
 * The interface Dao.
 *
 * @param <T> the type parameter
 */
public interface IDao<T> {
    /**
     * Save t.
     *
     * @param t the t
     * @return the t
     */
    T save(T t) ;

    /**
     * Get t.
     *
     * @param id the id
     * @return the t
     */
    T get(long id);

    /**
     * Update t.
     *
     * @param t the t
     * @return the t
     */
    T update(T t);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(long id);
}
