package com.example.jspcmsfinal.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.jspcmsfinal.db.DBConnectionPool;
import com.example.jspcmsfinal.dto.ComplainDto;
import com.example.jspcmsfinal.dto.UserDto;
import com.example.jspcmsfinal.model.ComplimentModel;
import com.example.jspcmsfinal.model.UserModel;
import com.example.jspcmsfinal.util.SessionHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            UserModel userModel = new UserModel();
            if(userModel.isEmailExist(email, DBConnectionPool.getConnection())){
                //EMAIL FOUND
                if(userModel.isLoginSuccess(email, password, DBConnectionPool.getConnection())){
                    //LOGIN TO DASHBOARD
                    HttpSession session = req.getSession();
                    if(userModel.isAdminLogin(email, DBConnectionPool.getConnection())){
                        //LOAD ADMIN COMPONENTS
                        SessionHelper.loadAdminComponents(req);

                        //ADMIN LOGIN
                        resp.sendRedirect("AdminDashboard.jsp");
                    }else {
                        //USER LOG IN

                        // Get full user data (from DB)
                        UserDto user = userModel.getUserByEmail(email, DBConnectionPool.getConnection());

                        // Start session and save user data
                        session.setAttribute("email", user.getEmail());
                        session.setAttribute("name", user.getName());
                        session.setAttribute("role", user.getRole());
                        session.setAttribute("uId", user.getUId());

                        //LOAD COMPLIMENTS
                        SessionHelper.loadComplimentsForUser(req);

                        resp.sendRedirect("userDashboard.jsp");
                    }
                }else{
                    //PASSWORD INCORRECT
                    alertAndRedirect(resp, "Incorrect password.");
                    return;
                }
            }else{
                //EMAIL NOT FOUND
                alertAndRedirect(resp, "Email Is Not Found");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void alertAndRedirect(HttpServletResponse resp, String message) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<script>");
        out.println("alert('" + message + "');");
        out.println("window.location='LoginPage.jsp';");
        out.println("</script>");
    }
}