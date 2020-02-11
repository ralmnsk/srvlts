package com.facultative.service;

/**
 * The interface Service.
 *
 * @param <T> the type parameter
 */
public interface IService<T> {
    /**
     * Save t.
     *
     * @param t the t
     * @return the t
     */
    T save(T t);

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
