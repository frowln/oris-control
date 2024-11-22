package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.entity.User;
import org.example.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Integer userId = userService.findByUsername(username).get().getId();
        if (userService.login(username, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user_id", userId);
            session.setAttribute("username", username);
            resp.sendRedirect("/profile");
        } else {
            req.setAttribute("errorMessage", "Неверное имя пользователя или пароль");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}