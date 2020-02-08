package com.facultative.dao;

import java.util.List;

public interface IDaoMark<T> extends IDao<T> {
    List<T> getMarksByTutorId(long tutorId, int pageNumber);
    List<T> getMarksByStudentId(long studentId, int pageNumber);
    int getCountMarksByTutorId(long tutorId);
    int getCountMarksByStudentId(long userId);
}
