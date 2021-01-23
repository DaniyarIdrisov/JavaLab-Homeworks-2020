package ru.kpfu.itis.group903.javalab.idrisov.daniyar.services;

import ru.kpfu.itis.group903.javalab.idrisov.daniyar.models.User;

import java.util.List;

public interface UsersService {

    List<User> getAllUsers();
    List<User> getAllUsersByAge(int age);

}
