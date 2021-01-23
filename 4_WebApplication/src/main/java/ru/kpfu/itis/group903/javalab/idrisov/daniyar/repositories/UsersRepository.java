package ru.kpfu.itis.group903.javalab.idrisov.daniyar.repositories;

import ru.kpfu.itis.group903.javalab.idrisov.daniyar.models.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User> {

    List<User> findAllByAge(int age);

}
