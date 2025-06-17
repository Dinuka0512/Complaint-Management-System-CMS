package com.example.jspcmsfinal.model;

import com.example.jspcmsfinal.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
