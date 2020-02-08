package com.facultative.service;

import java.util.List;

public interface IMarkService<T> extends IService<T> {
    List<T> getMarksByTutorId(long tutorId, int pageNumber);
    List<T> getMarksByStudentId(long studentId, int pageNumber);
    int getCountMarksByTutorId(long tutorId);
    int getCountMarksByStudentId(long userId);
}
