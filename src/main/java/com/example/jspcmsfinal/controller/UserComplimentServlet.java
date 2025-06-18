package com.example.jspcmsfinal.controller;

import com.example.jspcmsfinal.db.DBConnectionPool;
import com.example.jspcmsfinal.dto.ComplainDto;
import com.example.jspcmsfinal.model.ComplimentModel;
import com.example.jspcmsfinal.model.UserModel;
import com.example.jspcmsfinal.util.SessionHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/userCompliment")
public class UserComplimentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ComplimentModel complimentModel = new ComplimentModel();
            Connection connection = DBConnectionPool.getConnection();

            String action = req.getParameter("action");

            if ("save".equals(action)) {
                //SAVE NEW COMPLIMENT
                String message = req.getParameter("message");
                String subject = req.getParameter("subject");

                String uId = (String) req.getSession().getAttribute("uId");

                ComplainDto complainDto = new ComplainDto();
                complainDto.setMessage(message);
                complainDto.setSubject(subject);

                boolean saved = complimentModel.saveCompliment(uId, complainDto, connection);
                if(saved){
                    alertAndRedirectToPage(resp, "Compliment Saved!", "UserCompliments.jsp");
                }else {
                    alertAndRedirectToPage(resp, "Compliment is Not Saved!... \nSomthing Went Wrong..", "UserCompliments.jsp");
                }

            } else if ("update".equals(action)) {
                // update existing one
            } else if ("delete".equals(action)) {
                // delete based on ID
            }

            //LOAD COMPLIMENT TABLE
            SessionHelper.loadCompliments(req);

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            HttpSession session = req.getSession();
            ComplimentModel complimentModel = new ComplimentModel();
            String  uId = (String) session.getAttribute("uId");

            ArrayList<ComplainDto> allByUId = complimentModel.getAllByUId(uId, DBConnectionPool.getConnection());
            session.setAttribute("complimentList", allByUId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
