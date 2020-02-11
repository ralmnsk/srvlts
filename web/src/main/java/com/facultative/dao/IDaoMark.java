package com.facultative.dao;

import java.util.List;


/**
 * The interface Dao mark.
 *
 * @param <T> the type parameter
 */
public interface IDaoMark<T> extends IDao<T> {
    /**
     * Gets marks by tutor id.
     *
     * @param tutorId    the tutor id
     * @param pageNumber the page number
     * @return the marks by tutor id
     */
    List<T> getMarksByTutorId(long tutorId, int pageNumber, int scale);

    /**
     * Gets marks by student id.
     *
     * @param studentId  the student id
     * @param pageNumber the page number
     * @return the marks by student id
     */
    List<T> getMarksByStudentId(long studentId, int pageNumber, int scale);

    /**
     * Gets count marks by tutor id.
     *
     * @param tutorId the tutor id
     * @return the count marks by tutor id
     */
    int getCountMarksByTutorId(long tutorId);

    /**
     * Gets count marks by student id.
     *
     * @param userId the user id
     * @return the count marks by student id
     */
    int getCountMarksByStudentId(long userId);
}
