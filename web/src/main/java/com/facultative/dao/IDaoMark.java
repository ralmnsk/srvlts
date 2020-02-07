package com.facultative.dao;

import java.util.List;

public interface IDaoMark<T> extends IDao<T> {
    List<T> getMarksByTutorId(long tutorId);
    List<T> getMarksByStudentId(long studentId);
}
