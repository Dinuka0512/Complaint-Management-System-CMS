package com.example.jspcmsfinal.controller;

import java.io.*;
import java.sql.SQLException;

import com.example.jspcmsfinal.db.DBConnectionPool;
import com.example.jspcmsfinal.model.UserModel;
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
                    if(userModel.isAdminLogin(email, DBConnectionPool.getConnection())){
                        //ADMIN LOGIN
                        resp.sendRedirect("AdminDashboard.jsp");
                    }else {
                        //USER LOG IN
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