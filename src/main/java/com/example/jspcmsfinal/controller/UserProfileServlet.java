package com.example.jspcmsfinal.controller;

import com.example.jspcmsfinal.db.DBConnectionPool;
import com.example.jspcmsfinal.dto.UserDto;
import com.example.jspcmsfinal.model.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/userProfile")
public class UserProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String name = req.getParameter("name");
            String password = req.getParameter("password");

            //USER EMAIL
            HttpSession session = req.getSession();
            if(session!=null){
                String email = (String) session.getAttribute("email");
                resp.getWriter().println(name + " " + password + " " + email);
                UserModel userModel = new UserModel();
                UserDto user = userModel.getUserByEmail(email, DBConnectionPool.getConnection());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
