package com.example.jspcmsfinal.model;

import com.example.jspcmsfinal.dto.AnswerDto;
import com.example.jspcmsfinal.dto.ComplainDto;
import com.example.jspcmsfinal.dto.UserDto;
import com.example.jspcmsfinal.dto.tm.UserAnswerTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class UserModel {
    public int saveUser(UserDto dto, Connection connection) throws SQLException {
        //BEFORE SAVE NEED TO CHECK IS UNIQUE EMAIL FOR SAVE

        if(isUniqueEmail(dto.getEmail(), connection)){
            String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, UUID.randomUUID().toString());
            statement.setString(2, dto.getName());
            statement.setString(3, dto.getEmail());
            statement.setString(4, dto.getPassword());
            statement.setString(5, dto.getRole());

            return statement.executeUpdate();
        }else {
            return 0;
        }
    }

    public boolean isUniqueEmail(String email, Connection connection) throws SQLException {
        String sql = "SELECT * from user where email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);

        ResultSet resultSet = statement.executeQuery();
        return !resultSet.next();
    }

    public boolean isEmailExist(String email, Connection connection) throws SQLException {
        String sql = "SELECT * from user where email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);

        return statement.executeQuery().next();
    }

    public boolean isLoginSuccess(String email, String password, Connection connection) throws SQLException {
        String sql = "SELECT * from user where email = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,email);
        statement.setString(2, password);

        return statement.executeQuery().next();
    }

    public boolean isAdminLogin(String email, Connection connection) throws SQLException {
        String sql = "SELECT * from user where email = ? AND role = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, "admin");

        return statement.executeQuery().next();
    }

    public UserDto getUserByEmail(String email, Connection conn) throws SQLException {
        String sql = "SELECT * FROM user WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            UserDto user = new UserDto();
            user.setUId(rs.getString("uId"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            return user;
        }
        return null;
    }

    public boolean updateUser(String uId, String name , String password , Connection connection) throws SQLException {
        String sql = "UPDATE user set name = ? , password = ? where uId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, password);
        statement.setString(3, uId);

        return statement.executeUpdate() > 0;
    }

    public ArrayList<UserDto> getAll(Connection connection) throws SQLException {
        String sql = "SELECT * FROM user where role = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "user");

        ResultSet resultSet = statement.executeQuery();
        ArrayList<UserDto> userDtos = new ArrayList<>();
        while (resultSet.next()){
            UserDto userDto = new UserDto(
                    resultSet.getString("uId"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("role")
            );

            userDtos.add(userDto);
        }

        return userDtos;
    }

    public boolean deleteUser(String uId, Connection connection) throws SQLException {
        String sql = "DELETE FROM user where uId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, uId);

        return statement.executeUpdate() > 0;
    }

    public boolean updateUser(String id, String name,String email, String password, Connection connection) throws SQLException {
        String sql = "UPDATE user SET name = ?, email = ?, password = ? where uId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, email);
        statement.setString(3, password);
        statement.setString(4, id);

        return statement.executeUpdate() > 0;
    }

    public UserDto getUserById(String id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM user WHERE uId = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            UserDto user = new UserDto();
            user.setUId(rs.getString("uId"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            return user;
        }
        return null;
    }

    public ArrayList<UserAnswerTm> loadTableDataUserAnswer(String id, Connection connection) throws SQLException {
        UserModel userModel = new UserModel();
        ArrayList<UserAnswerTm> userAnswerTms = new ArrayList<>();

        UserDto user = userModel.getUserById(id, connection);
        if (user != null) {
            String sqlComplain = "SELECT * FROM complain WHERE uId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sqlComplain)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String complainId = resultSet.getString("complainId");

                        ComplainDto complainDto = new ComplainDto(
                                complainId,
                                resultSet.getString("uId"),
                                resultSet.getString("subject"),
                                resultSet.getString("message"),
                                resultSet.getString("status"),
                                resultSet.getString("date")
                        );

                        AnswerDto answerDto = null;

                        String sqlAnswer = "SELECT * FROM answer WHERE complainId = ?";
                        try (PreparedStatement statement1 = connection.prepareStatement(sqlAnswer)) {
                            statement1.setString(1, complainId);
                            try (ResultSet resultSet1 = statement1.executeQuery()) {
                                if (resultSet1.next()) {
                                    answerDto = new AnswerDto(
                                            resultSet1.getString("ansId"),
                                            complainId,
                                            resultSet1.getString("subject"),
                                            resultSet1.getString("message"),
                                            resultSet1.getString("date")
                                    );
                                }
                            }
                        }

                        // Add to list only if answer exists
                        if (answerDto != null) {
                            UserAnswerTm userAnswerTm = new UserAnswerTm(
                                    user.getEmail(),
                                    complainDto.getSubject(),
                                    complainDto.getMessage(),
                                    complainDto.getDate(),
                                    answerDto.getSubject(),
                                    answerDto.getMessage(),
                                    answerDto.getDate()
                            );
                            userAnswerTms.add(userAnswerTm);
                        }
                    }
                }
            }
        }

        return userAnswerTms;
    }
}
