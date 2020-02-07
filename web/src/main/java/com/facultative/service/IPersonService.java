package com.facultative.service;

import com.facultative.dao.IDao;

public interface IPersonService<T> extends IService<T> {
    T getByLogin(String login);
}
