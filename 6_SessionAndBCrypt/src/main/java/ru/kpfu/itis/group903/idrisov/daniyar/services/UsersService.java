package ru.kpfu.itis.group903.idrisov.daniyar.services;

import ru.kpfu.itis.group903.idrisov.daniyar.model.User;

import java.util.List;

public interface UsersService {

    List<User> getAllUsers();

    List<User> getUsersByUUID(String UUID);

    void addUser(User user);

    List<User> getUserByLogin(String login);

}
