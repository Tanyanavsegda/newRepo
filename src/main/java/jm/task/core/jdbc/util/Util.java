package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private Connection connection = null;
    public Util() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema","root", "1234");
        } catch (SQLException | ClassNotFoundException e) {
            e.getMessage();
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
