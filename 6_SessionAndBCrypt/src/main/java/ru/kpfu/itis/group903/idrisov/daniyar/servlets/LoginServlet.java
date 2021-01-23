package ru.kpfu.itis.group903.idrisov.daniyar.servlets;

import org.springframework.security.crypto.password.PasswordEncoder;
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

    private PasswordEncoder passwordEncoder;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
        this.passwordEncoder = (PasswordEncoder) servletContext.getAttribute("passwordEncoder");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        System.out.println("---------------------------------");
        System.out.println("Login: " + login);
        System.out.println("Password: " + password);

        List<User> users = usersService.getUserByLogin(login);
        if (users.isEmpty()) {
            System.out.println("---------------------------------");
            System.out.println("Пользователь не вошел в аккаунт!");
            resp.sendRedirect("/login");
            return;
        }
        User user = users.get(0);
        if (passwordEncoder.matches(password, user.getPassword())) {
            Cookie cookie = new Cookie("auth", user.getUUID());
            cookie.setMaxAge(900);
            resp.addCookie(cookie);

            session.setAttribute("current-user", login);

            System.out.println("---------------------------------");
            System.out.println("Пользователь успешно вошел!");
            resp.sendRedirect("/profile");
        } else {
            System.out.println("---------------------------------");
            System.out.println("Пользователь не вошел в аккаунт!");
            resp.sendRedirect("/login");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        Helper.render(req, resp, "login.ftl", root);
    }

}
