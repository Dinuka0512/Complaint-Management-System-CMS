package com.example.jspcmsfinal.model;

import com.example.jspcmsfinal.dto.ComplainDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

public class ComplimentModel {

    public boolean saveCompliment(String uId, ComplainDto complainDto, Connection connection) throws SQLException {
        String date = String.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));

        String sql = "INSERT INTO complain VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, UUID.randomUUID().toString());
            statement.setString(2, uId);
            statement.setString(3, complainDto.getSubject());
            statement.setString(4, complainDto.getMessage());
            statement.setString(5, "Pending");
            statement.setString(6, date);

            return statement.executeUpdate() > 0;
        }
    }

    public ArrayList<ComplainDto> getAllPending(Connection connection) throws SQLException {
        String sql = "SELECT * FROM complain where status = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Pending");
            ResultSet resultSet = statement.executeQuery();

            ArrayList<ComplainDto> complainDtos = new ArrayList<>();
            while (resultSet.next()) {
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
        String sql = "SELECT * FROM complain WHERE uId = ?";
        ArrayList<ComplainDto> complainDtos = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
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
        }
        return complainDtos;
    }

    public boolean deleteCompliment(String complainId, Connection connection) throws SQLException {
        try {
            String sql = "DELETE FROM complain WHERE complainId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, complainId);
                return statement.executeUpdate() > 0;
            }
        }finally {
            connection.close();
        }
    }

    public boolean updateCompliment(String complainId, String subject, String message, Connection connection) throws SQLException {
        String sql = "UPDATE complain SET subject = ?, message = ? WHERE complainId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, subject);
            statement.setString(2, message);
            statement.setString(3, complainId);
            return statement.executeUpdate() > 0;
        }
    }

    public int getAllComplimentsCountUser(String uId, Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) FROM complain WHERE uId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    public int getAllSolvedCompliment(String uId, Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) FROM complain WHERE uId = ? AND status = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uId);
            statement.setString(2, "Solved");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    public int getAllPendingCompliment(String uId, Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) FROM complain WHERE uId = ? AND status = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uId);
            statement.setString(2, "Pending");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    public boolean updateComplimentAsSolved(String complainId, Connection connection) throws SQLException {
        String sql = "UPDATE complain SET status = ? where complainId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "Solved");
        statement.setString(2, complainId);

        return statement.executeUpdate() > 0;
    }

    public boolean updateComplimentAsPending(String complainId, Connection connection) throws SQLException {
        String sql = "UPDATE complain SET status = ? where complainId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "Pending");
        statement.setString(2, complainId);

        return statement.executeUpdate() > 0;
    }

    public int getTotalCompliments(Connection connection) throws SQLException {
        String sql = "SELECT * FROM complain";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        int i = 0;
        while (resultSet.next()){
            i++;
        }

        return i;
    }

    public int getTotalSolvedCompliments(Connection connection) throws SQLException {
        String sql = "SELECT * FROM complain WHERE status = 'Solved'";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        int i = 0;
        while (resultSet.next()){
            i++;
        }

        return i;
    }

    public int getRecentCompliments(Connection connection) throws SQLException {
        // Assuming `date` is stored as 'dd MMM yyyy' (e.g., '19 Jun 2025')
        // Convert to MySQL date format to compare
        String sql = "SELECT * FROM complain WHERE STR_TO_DATE(date, '%d %b %Y') >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        int i = 0;
        while (resultSet.next()){
            i++;
        }

        return i;
    }

    public ComplainDto getDetailByComplainId(String id,Connection connection) throws SQLException {
        String sql = "SELECT * FROM complain WHERE complainId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            ComplainDto complainDto = new ComplainDto(
                    resultSet.getString("complainId"),
                    resultSet.getString("uId"),
                    resultSet.getString("subject"),
                    resultSet.getString("message"),
                    resultSet.getString("status"),
                    resultSet.getString("date")
            );

            return complainDto;
        }

        return null;
    }
}
