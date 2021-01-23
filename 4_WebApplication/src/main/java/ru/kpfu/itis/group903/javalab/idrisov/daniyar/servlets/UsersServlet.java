package ru.kpfu.itis.group903.javalab.idrisov.daniyar.servlets;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.kpfu.itis.group903.javalab.idrisov.daniyar.repositories.UsersRepository;
import ru.kpfu.itis.group903.javalab.idrisov.daniyar.repositories.UsersRepositoryJdbcImpl;
import ru.kpfu.itis.group903.javalab.idrisov.daniyar.services.UsersService;
import ru.kpfu.itis.group903.javalab.idrisov.daniyar.services.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init() throws ServletException {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/my_first_database");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("zxcdfg270301");
        hikariConfig.setMaximumPoolSize(5);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        this.usersService = new UsersServiceImpl(usersRepository);

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(usersService.getAllUsers());
        System.out.println(usersService.getAllUsersByAge(19));
    }

}