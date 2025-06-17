package com.example.jspcmsfinal.model;

import com.example.jspcmsfinal.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class UserModel {
    public int saveUser(UserDto dto, Connection connection) throws SQLException {
        String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, UUID.randomUUID().toString());
        statement.setString(2, dto.getName());
        statement.setString(3, dto.getEmail());
        statement.setString(4, dto.getPassword());
        statement.setString(5, dto.getRole());

        return statement.executeUpdate();
    }
}
