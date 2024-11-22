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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        if (userService.register(username, password)) {
            Optional<User> registeredUser = userService.findByUsername(username);
            HttpSession session = req.getSession();
            session.setAttribute("user_id", registeredUser.get().getId());
            session.setAttribute("username", registeredUser.get().getUsername());
            resp.sendRedirect("/profile");
        } else {
            req.setAttribute("errorMessage", "Пользователь с таким именем уже зарегистрирован");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }
    }
}
