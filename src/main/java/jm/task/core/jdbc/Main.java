package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();

        userDao.saveUser("Name1", "LastName1", (byte) 1);
        userDao.saveUser("Name2", "LastName2", (byte) 5);
        userDao.saveUser("Name3", "LastName3", (byte) 11);
        userDao.saveUser("Name4", "LastName4", (byte) 22);

        userDao.removeUserById(3);
        userDao.getAllUsers().stream().forEach(System.out::println);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }
}
