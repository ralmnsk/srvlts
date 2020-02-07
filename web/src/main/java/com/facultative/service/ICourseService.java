package com.facultative.service;

import java.util.List;

public interface ICourseService<T> extends IService<T> {
    List<T> getCoursesByTutorId(long tutorId);
    List<T> getCourses();
}
