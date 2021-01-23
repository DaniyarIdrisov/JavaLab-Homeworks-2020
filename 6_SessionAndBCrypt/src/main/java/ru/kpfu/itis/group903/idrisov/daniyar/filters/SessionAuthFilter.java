package ru.kpfu.itis.group903.idrisov.daniyar.filters;

import ru.kpfu.itis.group903.idrisov.daniyar.config.Helper;
import ru.kpfu.itis.group903.idrisov.daniyar.model.User;
import ru.kpfu.itis.group903.idrisov.daniyar.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebFilter(urlPatterns = {"/profile/*"})
public class SessionAuthFilter implements Filter {

    private UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("current-user");
        List<User> users = usersService.getAllUsers();
        User user = null;
        for (User u: users) {
            if (u.getLogin().equals(login)) {
                user = u;
            }
        }
        if (user == null) {
            System.out.println("---------------------------------");
            System.out.println("Пользователь не авторизован! 1");
            response.sendRedirect("/login");
            return;
        }

        System.out.println("---------------------------------");
        System.out.println(user);

        Map<String, Object> root = new HashMap<>();
        root.put("user", user);
        System.out.println("---------------------------------");
        System.out.println("Пользователь авторизован!");
        Helper.render(request, response, "profile.ftl", root);

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
