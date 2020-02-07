package com.facultative.dao;

public interface IDaoPerson<T> extends IDao<T> {
    T getByLogin(String login);
}
