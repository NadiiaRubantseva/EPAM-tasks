package com.epam.rd.java.basic.topic07.task01.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import com.epam.rd.java.basic.topic07.task01.Constants;
import com.epam.rd.java.basic.topic07.task01.db.entity.*;

import static com.epam.rd.java.basic.topic07.task01.db.Fields.*;

public class DBManager {

    private static final Logger LOGGER = Logger.getLogger(DBManager.class.getName());
    private static final String URL = "connection.url";
    private static final String URL_PATH = Constants.SETTINGS_FILE;
    private static DBManager dbManager;

    private DBManager() {
    }

    public static synchronized DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    private Connection getConnection() throws DBException {
        try (FileInputStream fio = new FileInputStream(URL_PATH)) {
            Properties properties = new Properties();
            properties.load(fio);
            return DriverManager.getConnection(properties.getProperty(DBManager.URL));
        } catch (SQLException | IOException e) {
            LOGGER.severe(e.getMessage());
            throw new DBException("failed to connect");
        }
    }

    public List<User> findAllUsers() throws DBException {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(Constants.FIND_ALL_USERS)) {
            while (resultSet.next()) {
                User user = getUser(resultSet.getString(USER_LOGIN));
                user.setId(resultSet.getInt(USER_ID));
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
        return users;
    }

    public void insertUser(User user) throws DBException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try (Connection connection = getConnection()) {
            ps = connection.prepareStatement(Constants.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getLogin());
            if (ps.executeUpdate() != 1) {
                return;
            }
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {
            LOGGER.severe("insert user : " + e.getMessage());
        } finally {
            close(rs);
            close(ps);
        }
    }

    public User getUser(String login) throws DBException {
        PreparedStatement ps = null;
        User user = null;
        ResultSet rs = null;
        try (Connection connection = getConnection()) {
            ps = connection.prepareStatement(Constants.GET_USER);
            ps.setString(1, login);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = User.createUser(rs.getString(USER_LOGIN));
                user.setId(rs.getInt(USER_ID));
            }
        } catch (SQLException e) {
            LOGGER.warning("No such user " + e.getMessage());
        } finally {
            close(ps);
            close(rs);
        }
        return user;
    }

    public List<Team> findAllTeams() throws DBException {
        List<Team> listTeams = new ArrayList<>();
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(Constants.FIND_ALL_TEAMS)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Team team = Team.createTeam(rs.getString(TEAM_NAME));
                team.setId(rs.getInt(TEAM_ID));
                listTeams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTeams;
    }

    public void insertTeam(Team team) throws DBException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try (Connection connection = getConnection()) {
            ps = connection.prepareStatement(Constants.INSERT_TEAM, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, team.getName());
            if (ps.executeUpdate() != 1) {
                return;
            }
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                team.setId(id);
            }

        } catch (SQLException e) {
            LOGGER.severe("insert team : " + e.getMessage());
        } finally {
            close(rs);
            close(ps);
        }
    }

    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    private void close(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }
}