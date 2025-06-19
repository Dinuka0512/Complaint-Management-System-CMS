package com.example.jspcmsfinal.controller;

import com.example.jspcmsfinal.db.DBConnectionPool;
import com.example.jspcmsfinal.model.AnswerModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/adminAnswer")
public class AdminAnswerManage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String thisPage = "AdminAnswerManage.jsp";
            String ansId = req.getParameter("ansId");

            AnswerModel answerModel = new AnswerModel();
            Connection connection = DBConnectionPool.getConnection();
            String action = req.getParameter("action");
            if("update".equals(action)){
                //ANSWER UPDATING
                String subject = req.getParameter("subject");
                String message = req.getParameter("message");

                boolean updated = answerModel.updateAnswer(ansId, subject, message, connection);
                if(updated){
                    alertAndRedirectToPage(resp, "Answer Updated!",thisPage);
                }else{
                    alertAndRedirectToPage(resp, "Answer Update Failed",thisPage);
                }
            } else if ("delete".equals(action)) {
                //HERE DELETE ANSWER
                boolean deleted = answerModel.deleteAnswer(ansId, connection);
                if(deleted){
                    alertAndRedirectToPage(resp, "Answer Deleted!",thisPage);
                }else{
                    alertAndRedirectToPage(resp, "Answer Delete Failed!",thisPage);
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
