package ru.kpfu.itis.group903.idrisov.daniyar.filters;

import ru.kpfu.itis.group903.idrisov.daniyar.config.Helper;
import ru.kpfu.itis.group903.idrisov.daniyar.model.User;
import ru.kpfu.itis.group903.idrisov.daniyar.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebFilter(urlPatterns = {"/profile/*"})
public class CookieAuthFilter implements Filter {

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

        Cookie[] cookies = request.getCookies();
        String cookieName = "auth";
        Cookie cookie = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }
        if (cookie == null) {
            System.out.println("---------------------------------");
            System.out.println("Пользователь не авторизован! 1");
            response.sendRedirect("/login");
            return;
        }

        List<User> users = usersService.getUsersByUUID(cookie.getValue());
        if (users.isEmpty()) {
            System.out.println("---------------------------------");
            System.out.println("Пользователь не авторизован! 2");
            response.sendRedirect("/login");
            return;
        }

        User user = users.get(0);
        System.out.println("---------------------------------");
        System.out.println(user);

        Map<String, Object> root = new HashMap<>();
        root.put("user", user);
        System.out.println("---------------------------------");
        System.out.println("Пользователь авторизован!");
        Helper.render(request, response, "profile.ftl", root);

        filterChain.doFilter(request, response);

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
