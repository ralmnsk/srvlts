package com.github.ralmnsk.srvlt.service;

import com.github.ralmnsk.srvlt.dao.DaoInterface;

import java.sql.SQLException;

public interface PersonServiceInterface<T> extends DaoInterface<T> {
    T getByLogin(String login);
}
