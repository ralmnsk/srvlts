package com.facultative.service;

import java.util.List;

/**
 * The interface Course service.
 *
 * @param <T> the type parameter
 */
public interface ICourseService<T> extends IService<T> {
    /**
     * Gets courses by tutor id.
     *
     * @param tutorId    the tutor id
     * @param pageNumber the page number
     * @return the courses by tutor id
     */
    List<T> getCoursesByTutorId(long tutorId,int pageNumber, int scale);

    /**
     * Gets courses.
     *
     * @param pageNumber the page number
     * @return the courses
     */
    List<T> getCourses(int pageNumber, int scale);

    /**
     * Gets count courses by tutor id.
     *
     * @param tutorId the tutor id
     * @return the count courses by tutor id
     */
    int getCountCoursesByTutorId(long tutorId);

    /**
     * Gets count courses.
     *
     * @return the count courses
     */
    int getCountCourses();
}
