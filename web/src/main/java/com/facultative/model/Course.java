package com.facultative.model;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private long id;
    private String name;
    private Tutor tutor;
    private Set<Mark> marks=new HashSet<Mark>();

    public Course() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Set<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }
}
