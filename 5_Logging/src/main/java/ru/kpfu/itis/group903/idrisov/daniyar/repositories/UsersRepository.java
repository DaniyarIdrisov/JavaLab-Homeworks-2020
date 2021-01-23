package ru.kpfu.itis.group903.idrisov.daniyar.repositories;

import ru.kpfu.itis.group903.idrisov.daniyar.model.User;

import java.util.List;

public interface UsersRepository extends CrudRepository {

    List<User> findByUUID(String UUID);
    List<User> findByLoginAndPassword(String login, String password);

}
