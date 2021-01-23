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
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

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
        String uuid = UUID.randomUUID().toString();
        String hashPassword = passwordEncoder.encode(password);

        System.out.println("---------------------------------");
        System.out.println("Login: " + login);
        System.out.println("Password: " + password);
        System.out.println("Hash password: " + hashPassword);
        System.out.println("Cookie: " + uuid);

        User user = User.builder().login(login).password(hashPassword).UUID(uuid).build();
        usersService.addUser(user);

        session.setAttribute("current-user", login);

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
