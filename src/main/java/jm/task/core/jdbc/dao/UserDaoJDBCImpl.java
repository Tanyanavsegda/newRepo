package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Util util = new Util();
    private int count = 1;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String query = "CREATE TABLE users (userId INT, name VARCHAR(255), lastName VARCHAR(255), age INT)";
        try {
            Statement statement = util.getConnection().createStatement();
            statement.execute(query);
        } catch (SQLException e) {

        }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE users";
        try {
            Statement statement = util.getConnection().createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = util.getConnection().prepareStatement(query);
            ps.setInt(1, count);
            count++;
            ps.setString(2, name);
            ps.setString(3, lastName);
            ps.setInt(4, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE userId=?";
        try {
            PreparedStatement ps = util.getConnection().prepareStatement(query);
            ps.setInt(1, (int) id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        User user;
        String query = "SELECT * FROM users";
        try {
            Statement statement = util.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                user = new User(rs.getString("name"), rs.getString("lastName"), rs.getByte("age"));
                user.setId(rs.getLong("userId"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return users;
    }

    public void cleanUsersTable() {
        String query = "DELETE FROM users";
        try {
            PreparedStatement ps = util.getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
