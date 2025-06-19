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

@WebServlet("/adminAnswer")
public class AdminAnswerManage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String thisPage = "AdminAnswerManage.jsp";
            String ansId = req.getParameter("ansId");
            String action = req.getParameter("action");

            AnswerModel answerModel = new AnswerModel();
            ComplimentModel complimentModel = new ComplimentModel();

            Connection connection = DBConnectionPool.getConnection();
            if("update".equals(action)){

                try{
                    //ANSWER UPDATING
                    String subject = req.getParameter("subject");
                    String message = req.getParameter("message");

                    boolean updated = answerModel.updateAnswer(ansId, subject, message, connection);
                    if(updated){
                        alertAndRedirectToPage(resp, "Answer Updated!",thisPage);
                    }else{
                        alertAndRedirectToPage(resp, "Answer Update Failed",thisPage);
                    }
                }finally {
                    connection.close();
                }

            } else if ("delete".equals(action)) {
                //HERE DELETE ANSWER --- THERE NEED TO UPDATE COMPLIMENT AS THE PENDING
                connection.setAutoCommit(false);
                try {
                    //GET ALL DTO FROM ANSWER
                    AnswerDto answerDto = answerModel.getAnswerById(ansId, connection);
                    if(answerDto!=null){
                        //NEED UPDATE COMPLAIN AS --- PENDING
                        boolean updated = complimentModel.updateComplimentAsPending(answerDto.getComplainId(), connection);
                        if(updated){
                            //DELETE ANSWER
                            boolean deleted = answerModel.deleteAnswer(ansId, connection);
                            if(deleted){
                                //ALL ARE OK
                                connection.commit();
                                alertAndRedirectToPage(resp, "Answer Deleted!", thisPage);
                            }else {
                                connection.rollback();
                                alertAndRedirectToPage(resp, "Answer Delete Failed!", thisPage);
                            }
                        }else{
                            connection.rollback();
                            alertAndRedirectToPage(resp, "Answer Delete Failed!", thisPage);
                        }
                    }else {
                        connection.rollback();
                        alertAndRedirectToPage(resp, "Answer Delete Failed!", thisPage);
                    }
                }finally {
                    connection.setAutoCommit(true);
                    connection.close();
                }

                //UI LOAD
                SessionHelper.loadAdminComponents(req);
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
