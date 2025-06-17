package com.example.jspcmsfinal.controller;

import com.example.jspcmsfinal.db.DBConnectionPool;
import com.example.jspcmsfinal.dto.UserDto;
import com.example.jspcmsfinal.model.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String userName = req.getParameter("name");
            String email = req.getParameter("email");
            String jobRole = req.getParameter("role");
            String password = req.getParameter("password");

            //SET DATA TO THE DTO OBJ
            UserDto userDto = new UserDto();
            userDto.setName(userName);
            userDto.setEmail(email);
            userDto.setPassword(password);
            userDto.setRole(jobRole);

            UserModel userModel = new UserModel();
            int result = userModel.saveUser(userDto, DBConnectionPool.getConnection());

            if(result < 0){
                resp.sendRedirect("LoginPage.jsp");
            }else {
                req.setAttribute("error", "Registration failed");
                req.getRequestDispatcher("SignUpPage.jsp").forward(req, resp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
