package com.facultative.model;

import java.util.HashSet;
import java.util.Set;


/**
 * The type Course.
 */
public class Course {
    private long id;
    private String name;
    private Tutor tutor;

    /**
     * Instantiates a new Course.
     */
    public Course() {
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
    public Tutor getTutor() {
        return tutor;
    }

    /**
     * Sets tutor.
     *
     * @param tutor the tutor
     */
    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

}
