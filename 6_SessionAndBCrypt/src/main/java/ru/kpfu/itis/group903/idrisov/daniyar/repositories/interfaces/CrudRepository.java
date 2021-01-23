package ru.kpfu.itis.group903.idrisov.daniyar.repositories.interfaces;

import java.util.List;

public interface CrudRepository<T> {

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> findById(Long id);

    List<T> findAll();

}