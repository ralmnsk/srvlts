package com.github.ralmnsk.srvlt.service;

import com.github.ralmnsk.srvlt.dao.DaoInterface;

import java.util.List;

public interface CourseServiceInterface<T> extends ServiceInterface<T> {
    List<T> getAllCourses(long tuitorId);
}
