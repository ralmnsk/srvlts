package com.facultative.model;


/**
 * The type Person.
 */
public class Person {
    private long id;
    private String surname;
    private String name;
    private String login;
    private String password;
    private Enum role;

    /**
     * Gets role.
     *
     * @return the role
     */
    public Enum getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Enum role) {
        this.role = role;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
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
     * Instantiates a new Person.
     */
    public Person() {
    }

    /**
     * Instantiates a new Person.
     *
     * @param surname the surname
     * @param name    the name
     */
    public Person(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    /**
     * Instantiates a new Person.
     *
     * @param id      the id
     * @param surname the surname
     * @param name    the name
     */
    public Person(long id, String surname, String name) {
        this.id = id;
        this.surname = surname;
        this.name = name;
    }
}
