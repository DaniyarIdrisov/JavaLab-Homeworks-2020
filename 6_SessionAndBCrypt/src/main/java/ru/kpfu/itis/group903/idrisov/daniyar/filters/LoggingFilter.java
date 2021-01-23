package ru.kpfu.itis.group903.idrisov.daniyar.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static javax.script.ScriptEngine.FILENAME;

@WebFilter("*")
public class LoggingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        LOGGER.info(request.getRequestURL().toString());
        LOGGER.info(request.getServletPath());
        LOGGER.info(request.getMethod());
        LOGGER.info(request.getHeader("User-Agent"));

        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }

}
