package com.example.jspcmsfinal.model;

import com.example.jspcmsfinal.dto.AnswerDto;
import com.example.jspcmsfinal.dto.ComplainDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public ArrayList<AnswerDto> getAll(Connection connection) throws SQLException {
        try{
            String sql = "SELECT * FROM answer";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<AnswerDto> answerDtos = new ArrayList<>();

            while (resultSet.next()){
                AnswerDto answerDto = new AnswerDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );

                answerDtos.add(answerDto);
            }

            return answerDtos;
        }finally {
            connection.close();
        }
    }

    public boolean deleteAnswer(String id, Connection connection) throws SQLException {
        String sql = "DELETE FROM answer WHERE ansId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);

        return statement.executeUpdate() > 0;
    }

    public boolean updateAnswer(String id, String subject, String message, Connection connection) throws SQLException {
        String sql = "UPDATE answer SET subject = ?, message = ? WHERE ansId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, subject);
        statement.setString(2, message);
        statement.setString(3, id);

        return statement.executeUpdate() > 0;
    }

    public AnswerDto getAnswerById(String ansId, Connection connection) throws SQLException {
        String sql = "SELECT * FROM answer where ansId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, ansId);

        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            AnswerDto answerDto = new AnswerDto(
                    resultSet.getString("ansId"),
                    resultSet.getString("complainId"),
                    resultSet.getString("subject"),
                    resultSet.getString("message"),
                    resultSet.getString("date")
            );

            return answerDto;
        }

        return null;
    }
}
