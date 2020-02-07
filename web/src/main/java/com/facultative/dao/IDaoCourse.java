package com.facultative.dao;

import java.util.List;

public interface IDaoCourse<T> extends IDao<T> {
    List<T> getCoursesByTutorId(long tutorId);
    List<T> getCourses();
}
