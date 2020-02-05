package com.github.ralmnsk.srvlt.service;

import com.github.ralmnsk.srvlt.dao.DaoCourse;
import com.github.ralmnsk.srvlt.dao.DaoCourseInterface;
import com.github.ralmnsk.srvlt.model.Course;

import java.util.List;

public class CourseService implements CourseServiceInterface<Course> {
    private DaoCourseInterface<Course> dao= DaoCourse.getInstance();

    private static volatile CourseServiceInterface<Course> instance;

    public static CourseServiceInterface<Course> getInstance() {
        CourseServiceInterface<Course> localInstance = instance;
        if (localInstance == null) {
            synchronized (CourseService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CourseService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public List<Course> getAllCourses(long tuitorId) {
        return dao.getAllCourses(tuitorId);
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
}
