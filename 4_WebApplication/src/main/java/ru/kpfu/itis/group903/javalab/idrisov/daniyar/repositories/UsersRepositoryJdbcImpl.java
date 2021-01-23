package ru.kpfu.itis.group903.javalab.idrisov.daniyar.repositories;

import ru.kpfu.itis.group903.javalab.idrisov.daniyar.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository{

    private static final String SQL_FIND_ALL = "select * from student";
    private static final String SQL_FIND_ALL_BY_AGE = "select * from student where age = ?";

    private SimpleJdbcTemplate simpleJdbcTemplate;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .group(row.getInt("group_number"))
            .build();

    @Override
    public List<User> findAllByAge(int age) {

        return simpleJdbcTemplate.query(SQL_FIND_ALL_BY_AGE, userRowMapper, age);

    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {

        return simpleJdbcTemplate.query(SQL_FIND_ALL, userRowMapper);

    }

}
