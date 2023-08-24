package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = null;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String create = "create table if not exists users (" +
                     " id int not null auto_increment," +
                     " name varchar(255)," +
                     " lastName varchar(255)," +
                     " age int," +
                     " primary key (`id`))";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(create);
            System.out.println("Таблица users создана!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "drop table users";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица users удалена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = String.format("insert into users (name, lastName, age) values('%s', '%s', '%d')", name, lastName, age);

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("New User added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = String.format("delete from users where id = %d", id);

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("User deleted to id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String query = "select * from users";

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                User user = new User();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setLastName(result.getString("lastName"));
                user.setAge(result.getByte("age"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String truncate = "truncate table users";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(truncate);
            System.out.println("Таблица Users полностью очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
