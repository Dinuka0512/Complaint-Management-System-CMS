package com.example.jspcmsfinal.util;

import com.example.jspcmsfinal.db.DBConnectionPool;
import com.example.jspcmsfinal.dto.AnswerDto;
import com.example.jspcmsfinal.dto.ComplainDto;
import com.example.jspcmsfinal.dto.UserDto;
import com.example.jspcmsfinal.model.AnswerModel;
import com.example.jspcmsfinal.model.ComplimentModel;
import com.example.jspcmsfinal.model.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SessionHelper {
    public static void loadComplimentsForUser(HttpServletRequest req) throws SQLException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            String uId = (String) session.getAttribute("uId");
            if (uId != null) {

                ComplimentModel complimentModel = new ComplimentModel();
                Connection connection = DBConnectionPool.getConnection();

                try {

                    ArrayList<ComplainDto> list = complimentModel.getAllByUId(uId, connection);
                    session.setAttribute("complimentList", list);

                    int allComplimentsCountUser = complimentModel.getAllComplimentsCountUser(uId, connection);
                    session.setAttribute("allCount", allComplimentsCountUser);

                    int allPendingComplement = complimentModel.getAllPendingCompliment(uId, connection);
                    session.setAttribute("pending", allPendingComplement);

                    int allSolvedComplement = complimentModel.getAllSolvedCompliment(uId, connection);
                    session.setAttribute("solved", allSolvedComplement);
                }finally {
                    connection.close();
                }
            }
        }
    }

    public static void loadAdminComponents(HttpServletRequest req) throws SQLException {
        HttpSession session = req.getSession(false);
        if (session != null) {

            UserModel userModel = new UserModel();
            ComplimentModel complimentModel = new ComplimentModel();
            AnswerModel answerModel = new AnswerModel();
            Connection connection = DBConnectionPool.getConnection();

            try {
                ArrayList<UserDto> all = userModel.getAll(connection);
                session.setAttribute("allUsers", all);

                ArrayList<ComplainDto> allComplains = complimentModel.getAllPending(connection);
                session.setAttribute("complains", allComplains);

                ArrayList<AnswerDto> answerDtos = answerModel.getAll(connection);
                session.setAttribute("answerDtos", answerDtos);

                int totalCompliments = complimentModel.getTotalCompliments(connection);
                session.setAttribute("comTotal", totalCompliments);

                int totalSolvedCompliments = complimentModel.getTotalSolvedCompliments(connection);
                session.setAttribute("comTotalSolve",totalSolvedCompliments);

                int recentCompliments = complimentModel.getRecentCompliments(connection);
                session.setAttribute("comThisWeek", recentCompliments);

            }finally {
                connection.close();
            }
        }
    }
}
