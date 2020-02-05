package com.github.ralmnsk.srvlt.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Course {
    private long id;
    private String name;
    private long tuitorId;
    private Set<Person> students=new LinkedHashSet<>();

    public Course(String name, long tuitorId) {
        this.name = name;
        this.tuitorId = tuitorId;
    }

    public Course() {
    }

    public long getTuitorId() {
        return tuitorId;
    }

    public void setTuitorId(long tuitorId) {
        this.tuitorId = tuitorId;
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

    public Set<Person> getStudents() {
        return students;
    }

    public void setStudents(Set<Person> students) {
        this.students = students;
    }
}
