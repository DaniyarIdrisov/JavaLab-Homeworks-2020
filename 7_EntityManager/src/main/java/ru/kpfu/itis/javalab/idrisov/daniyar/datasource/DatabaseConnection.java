package ru.kpfu.itis.javalab.idrisov.daniyar.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DatabaseConnection {

    public DataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/javalab-db");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("zxcdfg270301");
        hikariConfig.setMaximumPoolSize(5);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

}
