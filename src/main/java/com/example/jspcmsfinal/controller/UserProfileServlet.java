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
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/userProfile")
public class UserProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            UserModel userModel = new UserModel();
            String name = req.getParameter("name");
            String password = req.getParameter("password");

            //USER EMAIL
            HttpSession session = req.getSession();
            if(session!=null){
                String email = (String) session.getAttribute("email");
                UserDto user = userModel.getUserByEmail(email, DBConnectionPool.getConnection());

                //HERE UPDATE THE USER NAME AND PASSWORD
                boolean update = userModel.updateUser(user.getUId(),name, password, DBConnectionPool.getConnection());
                if(update){
                    session.setAttribute("name", name);
                    alertAndRedirectToPage(resp, "User Updated!", "UserProfileManage.jsp");
                }else{
                    //UPDATE SOMETHING WENT WRONG
                    alertAndRedirectToPage(resp, "Update Failed! Something went Wrong...", "UserProfileManage.jsp");
                    return;
                }
            }

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
