package ru.kpfu.itis.group903.idrisov.daniyar.services;

import ru.kpfu.itis.group903.idrisov.daniyar.model.User;
import ru.kpfu.itis.group903.idrisov.daniyar.repositories.interfaces.UsersRepository;

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
    public List<User> getUsersByUUID(String UUID) {
        return usersRepository.findByUUID(UUID);
    }

    @Override
    public void addUser(User user) {
        usersRepository.save(user);
    }

    @Override
    public List<User> getUserByLogin(String login) {
        return usersRepository.findByLogin(login);
    }

}
