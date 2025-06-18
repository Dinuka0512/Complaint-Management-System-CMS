package com.example.jspcmsfinal.model;

import com.example.jspcmsfinal.dto.ComplainDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public ArrayList<ComplainDto> getAll(Connection connection) throws SQLException {
        String sql = "SELECT * FROM complain";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<ComplainDto> complainDtos =new ArrayList<>();
        while (resultSet.next()){
            ComplainDto complainDto = new ComplainDto(
                    resultSet.getString("complainId"),
                    resultSet.getString("uId"),
                    resultSet.getString("subject"),
                    resultSet.getString("message"),
                    resultSet.getString("status"),
                    resultSet.getString("date")
            );

            complainDtos.add(complainDto);
        }
        return complainDtos;
    }

    public ArrayList<ComplainDto> getAllByUId(String uId, Connection connection) throws SQLException {
        String sql = "SELECT * FROM complain where uId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, uId);

        ResultSet resultSet = statement.executeQuery();
        ArrayList<ComplainDto> complainDtos =new ArrayList<>();
        while (resultSet.next()){
            ComplainDto complainDto = new ComplainDto(
                    resultSet.getString("complainId"),
                    resultSet.getString("uId"),
                    resultSet.getString("subject"),
                    resultSet.getString("message"),
                    resultSet.getString("status"),
                    resultSet.getString("date")
            );

            complainDtos.add(complainDto);
        }
        return complainDtos;
    }
}
