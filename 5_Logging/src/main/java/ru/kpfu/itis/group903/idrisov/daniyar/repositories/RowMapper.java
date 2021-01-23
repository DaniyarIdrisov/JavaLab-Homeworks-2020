package ru.kpfu.itis.group903.idrisov.daniyar.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

    T mapRow(ResultSet row) throws SQLException;

}