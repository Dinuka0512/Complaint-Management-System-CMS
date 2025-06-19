package com.example.jspcmsfinal.controller;

import com.example.jspcmsfinal.db.DBConnectionPool;
import com.example.jspcmsfinal.dto.AnswerDto;
import com.example.jspcmsfinal.model.AnswerModel;
import com.example.jspcmsfinal.model.ComplimentModel;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@WebServlet("/adminCompliment")
public class AdminComplimentsManage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String thisPage = "AdminComplimentManage.jsp";

            ComplimentModel complimentModel = new ComplimentModel();

            String complainId = req.getParameter("complainId");
            String action = req.getParameter("action");

            if("delete".equals(action)){
                //DELETE COMPLIMENT
                boolean deleted = complimentModel.deleteCompliment(complainId, DBConnectionPool.getConnection());
                if(deleted){
                    SessionHelper.loadAdminComponents(req);
                    alertAndRedirectToPage(resp, "User Deleted", thisPage);
                }else{
                    alertAndRedirectToPage(resp, "User Delete Failed", thisPage);
                }
            }else {
                Connection connection = DBConnectionPool.getConnection();
                try {
                    //THERE NEED TO UPDATE THE COMPLAIN AS SOLVED AND CREATE NEW ANSWER
                    connection.setAutoCommit(false);

                    //UPDATE COMPLAIN
                    boolean updated = complimentModel.updateComplimentAsSolved(complainId, connection);
                    if(updated){
                        //NOW SAVE THE ANSWER
                        String message = req.getParameter("message");
                        String subject = req.getParameter("subject");

                        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));

                        AnswerDto answerDto = new AnswerDto();
                        answerDto.setComplainId(complainId);
                        answerDto.setSubject(subject);
                        answerDto.setMessage(message);
                        answerDto.setDate(date);

                        AnswerModel answerModel = new AnswerModel();
                        boolean answerSaved = answerModel.saveAnswer(answerDto, connection);
                        if(answerSaved){
                           //ALL ARE OK
                            connection.commit();
                            SessionHelper.loadAdminComponents(req);
                            alertAndRedirectToPage(resp, "Compiment Solved!", thisPage);

                        }else if("solve".equals(action)){
                            alertAndRedirectToPage(resp, "Something Went wrong...", thisPage);
                            connection.rollback();
                        }
                    }else {
                        alertAndRedirectToPage(resp, "Update Failed", thisPage);
                        connection.rollback();
                    }

                }finally {
                    connection.setAutoCommit(true);
                    connection.close();
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
