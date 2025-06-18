package com.example.jspcmsfinal.util;

import com.example.jspcmsfinal.db.DBConnectionPool;
import com.example.jspcmsfinal.dto.ComplainDto;
import com.example.jspcmsfinal.model.ComplimentModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.ArrayList;

public class SessionHelper {
    public static void loadComplimentsForUser(HttpServletRequest req) throws SQLException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            String uId = (String) session.getAttribute("uId");
            if (uId != null) {
                ArrayList<ComplainDto> list = new ComplimentModel().getAllByUId(uId, DBConnectionPool.getConnection());
                session.setAttribute("complimentList", list);
            }
        }
    }
}
