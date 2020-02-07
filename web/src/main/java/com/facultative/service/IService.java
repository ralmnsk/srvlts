package com.facultative.service;

import java.sql.SQLException;

public interface IService<T> {
    T save(T t);
    T get(long id);
    T update(T t);
    void delete(long id);
}
