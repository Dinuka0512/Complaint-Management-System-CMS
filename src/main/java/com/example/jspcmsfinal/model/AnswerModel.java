package com.example.jspcmsfinal.model;

import com.example.jspcmsfinal.dto.AnswerDto;
import com.example.jspcmsfinal.dto.ComplainDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class AnswerModel {
    public boolean saveAnswer(AnswerDto answerDto, Connection connection) throws SQLException {
        String sql = "INSERT INTO answer VALUES(?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, UUID.randomUUID().toString());
        statement.setString(2, answerDto.getComplainId());
        statement.setString(3, answerDto.getSubject());
        statement.setString(4, answerDto.getMessage());
        statement.setString(5, answerDto.getDate());

        return statement.executeUpdate() > 0;
    }
}
