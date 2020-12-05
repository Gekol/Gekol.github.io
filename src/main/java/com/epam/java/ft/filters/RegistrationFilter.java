package com.epam.java.ft.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationFilter implements Filter {

    private boolean validateText(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    private boolean validatePost(ServletRequest servletRequest) {
        String name = servletRequest.getParameter("first_name");
        if (!validateText(name, "^[a-zA-Zа-яюА-ЯЮ]+$")) {
            return false;
        }
        String surname = servletRequest.getParameter("last_name");
        if (!validateText(surname, "^[a-zA-Zа-яюА-ЯЮ]+$")) {
            return false;
        }
        String email = servletRequest.getParameter("email");
        if (!validateText(email, "@")) {
            return false;
        }
        String password = servletRequest.getParameter("password");
        return validateText(password, "^[a-zA-Z0-9]{8,20}$");
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        if (((HttpServletRequest) servletRequest).getMethod().equals("POST")) {
            if (servletRequest.getParameter("first_name") == null || validatePost(servletRequest)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                PrintWriter out = servletResponse.getWriter();
                out.print("Email or password is incorrect!!!");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
