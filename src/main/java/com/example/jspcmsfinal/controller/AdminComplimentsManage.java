package com.example.jspcmsfinal.controller;

import com.example.jspcmsfinal.db.DBConnectionPool;
import com.example.jspcmsfinal.model.ComplimentModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/adminCompliment")
public class AdminComplimentsManage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String thisPage = "AdminComplimentManage.jsp";
            ComplimentModel complimentModel = new ComplimentModel();
            Connection connection = null;
            connection = DBConnectionPool.getConnection();
            String action = req.getParameter("action");
            String complainId = req.getParameter("complainId");

            if("delete".equals(action)){
                //DELETE COMPLIMENT
                boolean deleted = complimentModel.deleteCompliment(complainId, connection);
                if(deleted){
                    alertAndRedirectToPage(resp, "User Deleted", thisPage);
                }else{
                    alertAndRedirectToPage(resp, "User Delete Failed", thisPage);
                }
            }else {
                //THERE NEED TO UPDATE THE COMPLAIN AS SOLVED AND CREATE NEW ANSWER
                connection.setAutoCommit(false);

                //UPDATE COMPLAIN


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
