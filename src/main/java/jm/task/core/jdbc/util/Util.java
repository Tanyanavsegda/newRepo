package jm.task.core.jdbc.util;

import java.sql.*;


public class Util {
    // реализуйте настройку соеденения с БД

    public Util() {

    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema","root", "1234");
            connection.setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            e.getMessage();
        }
        return connection;
    }

}
