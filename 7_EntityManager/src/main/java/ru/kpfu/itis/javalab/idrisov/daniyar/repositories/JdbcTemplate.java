package ru.kpfu.itis.javalab.idrisov.daniyar.repositories;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

    DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void query(String sql) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            System.out.println("--------------------------------------");
            System.out.println(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new IllegalStateException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    public <T> T findById(String sql, Class<T> resultType) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            System.out.println("--------------------------------------");
            System.out.println(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            Field[] fields = resultType.getDeclaredFields();
            Class<?>[] parameterTypes = new Class[fields.length];
            Object[] p = new Object[fields.length];

            resultSet.next();

            for (int i = 0; i < fields.length; i++) {
                parameterTypes[i] = fields[i].getType();
                p[i] = resultSet.getObject(fields[i].getName(), fields[i].getType());
            }

            return resultType.getConstructor(parameterTypes).newInstance(p);

        } catch (SQLException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new IllegalStateException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

}
