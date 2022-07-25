package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection con = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String query = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age INT)";
        try (Statement statement = con.createStatement()) {
            statement.execute(query);
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {

            }
        }

    }

    public void dropUsersTable() {
        String query = "DROP TABLE users";
        try (Statement statement = con.createStatement()) {
            statement.execute(query);
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {

            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {

            }
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (int) id);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {

            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        User user;
        String query = "SELECT * FROM users";
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                user = new User(rs.getString("name"), rs.getString("lastName"), rs.getByte("age"));
                user.setId(rs.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return users;
    }

    public void cleanUsersTable() {
        String query = "DELETE FROM users";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {

            }
        }
    }
}
