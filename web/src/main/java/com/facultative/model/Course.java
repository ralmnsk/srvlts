package com.facultative.model;

/**
 * The type Course.
 */
public class Course {
    private long id;
    private String name;
    private String description;
    private Person tutor;

    /**
     * Instantiates a new Course.
     */
    public Course() {
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets tutor.
     *
     * @return the tutor
     */
    public Person getTutor() {
        return tutor;
    }

    /**
     * Sets tutor.
     *
     * @param tutor the tutor
     */
    public void setTutor(Person tutor) {
        this.tutor = tutor;
    }

}
