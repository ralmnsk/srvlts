package com.github.ralmnsk.srvlt.dao;

import java.sql.SQLException;

public interface DaoInterface<T> {
    T save(T t) ;
    T get(long id);
    T update(T t);
    void delete(long id);
}
