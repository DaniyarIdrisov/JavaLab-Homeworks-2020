package ru.kpfu.itis.group903.idrisov.daniyar.servlets;

import ru.kpfu.itis.group903.idrisov.daniyar.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("auth", "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);

        System.out.println("---------------------------------");
        System.out.println("Пользователь вышел из аккунта!");
        resp.sendRedirect("/login");
    }


}
