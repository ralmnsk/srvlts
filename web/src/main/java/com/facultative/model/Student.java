package com.facultative.model;

import java.util.HashSet;
import java.util.Set;

public class Student extends Person{

    private Set<Mark> marks=new HashSet<Mark>();

    public Set<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }
}
