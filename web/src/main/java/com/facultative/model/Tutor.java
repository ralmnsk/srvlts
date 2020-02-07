package com.facultative.model;

import java.util.HashSet;
import java.util.Set;

public class Tutor extends Person{
    private Set<Course> course=new HashSet<Course>();

    public Set<Course> getCourse() {
        return course;
    }

    public void setCourse(Set<Course> course) {
        this.course = course;
    }
}
