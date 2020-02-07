package com.facultative.dao;

import java.sql.SQLException;

public interface IDao<T> {
    T save(T t) ;
    T get(long id);
    T update(T t);
    void delete(long id);
}
