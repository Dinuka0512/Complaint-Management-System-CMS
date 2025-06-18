package com.example.jspcmsfinal.controller;

import com.example.jspcmsfinal.db.DBConnectionPool;
import com.example.jspcmsfinal.dto.UserDto;
import com.example.jspcmsfinal.model.UserModel;
import com.example.jspcmsfinal.util.SessionHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/adminUser")
public class AdminUserManageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String userId = req.getParameter("userId");
        String name = req.getParameter("userName");
        String email = req.getParameter("userEmail");
        String password = req.getParameter("userPassword");
        String thisPage = "AdminUserManage.jsp";

        try {
            Connection connection = DBConnectionPool.getConnection();
            UserModel userModel = new UserModel();

            if("delete".equals(action)){
                //DELETE USER
                boolean deleted = userModel.deleteUser(userId, connection);
                if(deleted){
                    alertAndRedirectToPage(resp, "User Deleted", thisPage);
                }else{
                    alertAndRedirectToPage(resp, "User Delete failed \nSomething Went wrong", thisPage);
                }

            }else if ("update".equals(action)){
                //UPDATE USER
                boolean updated = userModel.updateUser(userId, name, email, password, connection);
                if(updated){
                    alertAndRedirectToPage(resp, "User Updated", thisPage);
                }else {
                    alertAndRedirectToPage(resp, "User Update Failed \nSomething Went wrong", thisPage);
                }

            }else if ("save".equals(action)){
                //SAVE USER
                UserDto userDto = new UserDto();
                userDto.setName(name);
                userDto.setPassword(password);
                userDto.setRole("user");
                userDto.setEmail(email);

                if(!userModel.isEmailExist(email, connection)){
                    int result = userModel.saveUser(userDto, connection);

                    if(result > 0){
                        alertAndRedirectToPage(resp, "User Saved!", thisPage);
                    }else{
                        alertAndRedirectToPage(resp, "User Save Failed \nSomething Went wrong", thisPage);
                    }
                }else {
                    alertAndRedirectToPage(resp, "This Email allready have...", thisPage);
                }
            }

            SessionHelper.loadAdminComponents(req);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void alertAndRedirectToPage(HttpServletResponse resp, String message, String targetPage) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.println("<script>");
        out.println("alert('" + message + "');");
        out.println("window.location='" + targetPage + "';");
        out.println("</script>");
    }
}
