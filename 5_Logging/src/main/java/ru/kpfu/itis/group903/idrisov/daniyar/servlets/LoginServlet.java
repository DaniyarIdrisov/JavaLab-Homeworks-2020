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
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

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
        System.out.println("---------------------------------");
        System.out.println("Login: " + login);
        System.out.println("Password: " + password);

        List<User> users = usersService.getUserByLoginAndPassword(login, password);
        if (users.isEmpty()) {
            System.out.println("---------------------------------");
            System.out.println("Пользователь не вошел в аккаунт!");
            resp.sendRedirect("/login");
        }
        else {
            User user = users.get(0);
            System.out.println("---------------------------------");
            System.out.println(user);
            if (user != null) {
                Cookie cookie = new Cookie("auth", user.getUUID());
                cookie.setMaxAge(900);
                resp.addCookie(cookie);
                System.out.println("---------------------------------");
                System.out.println("Пользователь успешно вошел!");
                resp.sendRedirect("/profile");
            }
            else {
                System.out.println("---------------------------------");
                System.out.println("Пользователь не вошел в аккаунт!");
                resp.sendRedirect("/login");
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        Helper.render(req, resp, "login.ftl", root);
    }

}
