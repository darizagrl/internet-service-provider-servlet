package com.provider.controller.filters;

import com.provider.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.isNull;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String requestURI = req.getRequestURI();
        String destination = "/login";
        if (isNull(session.getAttribute("user"))) {
            if (requestURI.contains("/admin") || requestURI.contains("/user")) {
                request.getRequestDispatcher(destination).forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } else if (requestURI.contains("/admin/")) {
            Integer role = ((User) session.getAttribute("user")).getRole().getId();
            if (role == 1) {
                chain.doFilter(request, response);
            } else {
                request.getRequestDispatcher(destination).forward(request, response);
            }
        } else if (requestURI.contains("/user/")) {
            Integer role = ((User) session.getAttribute("user")).getRole().getId();
            if (role == 2) {
                chain.doFilter(request, response);
            } else {
                request.getRequestDispatcher(destination).forward(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
