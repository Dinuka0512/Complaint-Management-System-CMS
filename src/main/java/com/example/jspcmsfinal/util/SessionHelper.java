package com.example.jspcmsfinal.util;

import com.example.jspcmsfinal.db.DBConnectionPool;
import com.example.jspcmsfinal.dto.ComplainDto;
import com.example.jspcmsfinal.dto.UserDto;
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

                ArrayList<ComplainDto> list = complimentModel.getAllByUId(uId, connection);
                session.setAttribute("complimentList", list);

                int allComplimentsCountUser = complimentModel.getAllComplimentsCountUser(uId, connection);
                session.setAttribute("allCount", allComplimentsCountUser);

                int allPendingComplement = complimentModel.getAllPendingCompliment(uId, connection);
                session.setAttribute("pending", allPendingComplement);

                int allSolvedComplement = complimentModel.getAllSolvedCompliment(uId, connection);
                session.setAttribute("solved", allSolvedComplement);
            }
        }
    }

    public static void loadAdminComponents(HttpServletRequest req) throws SQLException {
        HttpSession session = req.getSession(false);
        if (session != null) {

            UserModel userModel = new UserModel();
            ComplimentModel complimentModel = new ComplimentModel();
            Connection connection = DBConnectionPool.getConnection();

            ArrayList<UserDto> all = userModel.getAll(connection);
            session.setAttribute("allUsers", all);

            ArrayList<ComplainDto> allComplains = complimentModel.getAll(connection);
            session.setAttribute("complains", allComplains);
        }
    }
}
