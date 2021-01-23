package ru.kpfu.itis.group903.idrisov.daniyar.repositories.interfaces;

import ru.kpfu.itis.group903.idrisov.daniyar.model.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User> {

    List<User> findByUUID(String UUID);

    List<User> findByLogin(String login);

}
