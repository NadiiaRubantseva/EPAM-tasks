package com.epam.rd.java.basic.topic07.task02;

public class Constants {
    public static final String SETTINGS_FILE = "app.properties";
    public static final String FIND_ALL_USERS = "SELECT * FROM users";
    public static final String FIND_ALL_TEAMS = "SELECT * FROM teams";
    public static final String INSERT_USER = "INSERT INTO users (login) VALUES (?)";
    public static final String INSERT_TEAM = "INSERT INTO teams (name) VALUES (?)";
    public static final String GET_USER = "SELECT * FROM users WHERE login = ?";
    public static final String GET_TEAM = "SELECT * FROM teams WHERE name = ?";
    private static final String INSERT_INTO = "INSERT INTO ";
    private static final String WHERE = " WHERE ";
    public static final String TABLE_USERS_TEAMS = "users_teams";
    public static final String TEAM_ID = "team_id";
    public static final String USER_ID = "user_id";
    public static final String INSERT_USER_TO_TEAM = INSERT_INTO + TABLE_USERS_TEAMS + " VALUES(?,?)";
    public static final String GET_USER_TEAMS_ID = "SELECT " + TEAM_ID + " FROM " + TABLE_USERS_TEAMS + WHERE + USER_ID + "=?";

}
