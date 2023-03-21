package com.epam.rd.java.basic.topic07.task01;

public class Constants {
    public static final String SETTINGS_FILE = "app.properties";
    public static final String FIND_ALL_USERS = "SELECT * FROM users";
    public static final String FIND_ALL_TEAMS = "SELECT * FROM teams";
    public static final String INSERT_USER = "INSERT INTO users (login) VALUES (?)";
    public static final String INSERT_TEAM = "INSERT INTO teams (name) VALUES (?)";
    public static final String GET_USER = "SELECT * FROM users WHERE login = ?";

}
