package com.example.jspcmsfinal.model;

import com.example.jspcmsfinal.dto.ComplainDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class ComplimentModel {
    public boolean saveCompliment(String uId, ComplainDto complainDto, Connection connection) throws SQLException {
        String date = String.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));

        String sql = "INSERT INTO complain VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, UUID.randomUUID().toString());
        statement.setString(2, uId);
        statement.setString(3, complainDto.getSubject());
        statement.setString(4, complainDto.getMessage());
        statement.setString(5, "Pending");
        statement.setString(6, date);

        return statement.executeUpdate() > 0;
    }
}
