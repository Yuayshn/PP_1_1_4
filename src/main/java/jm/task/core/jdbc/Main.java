package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        userService.createUsersTable();

        userService.saveUser("Djo", "Baiden", (byte) 78);
        userService.saveUser("Tramp", "Donald", (byte) 74);
        userService.saveUser("Barak", "Obama", (byte) 59);
        userService.saveUser("Djordj", "Bush", (byte) 74);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}