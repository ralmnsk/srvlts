package com.facultative.model;


/**
 * The type Mark.
 */
public class Mark {
    private long id;
    private Person student;
    private Course course;
    private int mark;
    private String review;

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets student.
     *
     * @return the student
     */
    public Person getStudent() {
        return student;
    }

    /**
     * Sets student.
     *
     * @param student the student
     */
    public void setStudent(Person student) {
        this.student = student;
    }

    /**
     * Gets course.
     *
     * @return the course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Sets course.
     *
     * @param course the course
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Gets mark.
     *
     * @return the mark
     */
    public int getMark() {
        return mark;
    }

    /**
     * Sets mark.
     *
     * @param mark the mark
     */
    public void setMark(int mark) {
        this.mark = mark;
    }

    /**
     * Gets review.
     *
     * @return the review
     */
    public String getReview() {
        return review;
    }

    /**
     * Sets review.
     *
     * @param review the review
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * Instantiates a new Mark.
     */
    public Mark() {
    }


}
