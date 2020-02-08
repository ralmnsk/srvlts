package com.facultative.service;

import com.facultative.dao.DaoMarkImpl;
import com.facultative.dao.IDaoMark;
import com.facultative.model.Mark;

import java.util.List;

public class MarkServiceImpl implements IMarkService<Mark> {
    private IDaoMark<Mark> dao= DaoMarkImpl.getInstance();
    private static volatile IMarkService<Mark> instance;

    public static IMarkService<Mark> getInstance() {
        if (instance == null) {
            synchronized (MarkServiceImpl.class) {
                instance = instance;
                if (instance == null) {
                    instance = new MarkServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Mark save(Mark mark) {
        return dao.save(mark);
    }

    @Override
    public Mark get(long id) {
        return dao.get(id);
    }

    @Override
    public Mark update(Mark mark) {
        return dao.update(mark);
    }

    @Override
    public void delete(long id) {
        dao.delete(id);
    }

    @Override
    public List<Mark> getMarksByTutorId(long tutorId, int pageNumber) {
        return dao.getMarksByTutorId(tutorId, pageNumber);
    }

    @Override
    public List<Mark> getMarksByStudentId(long studentId, int pageNumber) {
        return dao.getMarksByStudentId(studentId, pageNumber);
    }

    @Override
    public int getCountMarksByTutorId(long tutorId) {
        return dao.getCountMarksByTutorId(tutorId);
    }

    @Override
    public int getCountMarksByStudentId(long userId) {
        return dao.getCountMarksByStudentId(userId);
    }
}
