package com.github.ralmnsk.srvlt.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Person {
    private long id;
    private String surname;
    private String name;
    private String login;
    private String password;
    private Enum role;
    private Set<Course> cources = new LinkedHashSet<>();

    public Set<Course> getCources() {
        return cources;
    }

    public void setCources(Set<Course> cources) {
        this.cources = cources;
    }

    public Enum getRole() {
        return role;
    }

    public void setRole(Enum role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person() {
    }

    public Person(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    public Person(long id, String surname, String name) {
        this.id = id;
        this.surname = surname;
        this.name = name;
    }
}
