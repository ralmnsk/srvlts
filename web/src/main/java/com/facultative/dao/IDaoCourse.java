package com.facultative.dao;

import java.util.List;

public interface IDaoCourse<T> extends IDao<T> {
    List<T> getCoursesByTutorId(long tutorId, int pageNumber);
    List<T> getCourses();
    int getCountCoursesByTutorId(long tutorId);
}
