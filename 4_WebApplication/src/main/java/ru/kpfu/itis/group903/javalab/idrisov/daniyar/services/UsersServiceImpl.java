package ru.kpfu.itis.group903.javalab.idrisov.daniyar.services;

import ru.kpfu.itis.group903.javalab.idrisov.daniyar.models.User;
import ru.kpfu.itis.group903.javalab.idrisov.daniyar.repositories.UsersRepository;

import java.util.List;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<User> getAllUsersByAge(int age) {
        return usersRepository.findAllByAge(age);
    }

}
