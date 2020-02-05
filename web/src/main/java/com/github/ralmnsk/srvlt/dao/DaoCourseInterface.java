package com.github.ralmnsk.srvlt.dao;

import java.util.List;

public interface DaoCourseInterface<T> extends DaoInterface<T> {
    List<T> getAllCourses(long tuitorId);
}
