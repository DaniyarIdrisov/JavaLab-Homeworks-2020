package ru.kpfu.itis.group903.idrisov.daniyar.servlets;

import ru.kpfu.itis.group903.idrisov.daniyar.config.Helper;
import ru.kpfu.itis.group903.idrisov.daniyar.model.User;
import ru.kpfu.itis.group903.idrisov.daniyar.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String uuid = UUID.randomUUID().toString();

        System.out.println("---------------------------------");
        System.out.println("Login: " + login);
        System.out.println("Password: " + password);
        System.out.println("Cookie: " + uuid);

        User user = User.builder().login(login).password(password).UUID(uuid).build();
        usersService.addUser(user);

        Cookie cookie = new Cookie("auth", uuid);
        cookie.setMaxAge(900);
        resp.addCookie(cookie);
        System.out.println("---------------------------------");
        System.out.println("Пользователь успешно зарегистрирован!");
        resp.sendRedirect("/profile");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        Helper.render(req, resp, "registration.ftl", root);
    }

}
