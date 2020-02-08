package com.facultative.service;

import java.util.List;

public interface ICourseService<T> extends IService<T> {
    List<T> getCoursesByTutorId(long tutorId,int pageNumber);
    List<T> getCourses();
    int getCountCoursesByTutorId(long tutorId);
}
