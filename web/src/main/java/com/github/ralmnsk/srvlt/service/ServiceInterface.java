package com.github.ralmnsk.srvlt.service;

import java.sql.SQLException;

public interface ServiceInterface<T> {
    T save(T t);
    T get(long id);
    T update(T t);
    void delete(long id);
}
