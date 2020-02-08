package com.facultative.service;

import com.facultative.dao.DaoCourseImpl;
import com.facultative.dao.IDaoCourse;
import com.facultative.model.Course;
import java.util.List;

public class CourseServiceImpl implements ICourseService<Course> {
    private IDaoCourse<Course> dao= DaoCourseImpl.getInstance();
    private static volatile ICourseService<Course> instance;

    public static ICourseService<Course> getInstance() {
        if (instance == null) {
            synchronized (CourseServiceImpl.class) {
                instance = instance;
                if (instance == null) {
                    instance = new CourseServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Course> getCoursesByTutorId(long tutorId, int pageNumber) {
        return dao.getCoursesByTutorId(tutorId, pageNumber);
    }

    @Override
    public Course save(Course course) {
        return dao.save(course);
    }

    @Override
    public Course get(long id) {
        return dao.get(id);
    }

    @Override
    public Course update(Course course) {
        return dao.update(course);
    }

    @Override
    public void delete(long id) {
        dao.delete(id);
    }

    @Override
    public List<Course> getCourses() {
        return dao.getCourses();
    }

    @Override
    public int getCountCoursesByTutorId(long tutorId) {
        return dao.getCountCoursesByTutorId(tutorId);
    }
}
