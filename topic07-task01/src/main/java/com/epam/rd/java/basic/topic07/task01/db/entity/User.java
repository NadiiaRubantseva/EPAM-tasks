package com.epam.rd.java.basic.topic07.task01.db.entity;

import java.util.Objects;

public class User {
    private int id;
    private final String login;

    public User(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public static User createUser(String login) {
        return new User(0, login);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }

    @Override
    public String toString() {
        return login;
    }

}