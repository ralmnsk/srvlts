package com.facultative.service;

import java.util.List;

public interface IMarkService<T> extends IService<T> {
    List<T> getMarksByTutorId(long tutorId);
    List<T> getMarksByStudentId(long studentId);
}
