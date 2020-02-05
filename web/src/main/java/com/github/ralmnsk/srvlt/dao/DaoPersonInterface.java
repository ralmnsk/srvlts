package com.github.ralmnsk.srvlt.dao;

import java.sql.SQLException;

public interface DaoPersonInterface<T> extends DaoInterface<T> {
    T getByLogin(String login);
}
