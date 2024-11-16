package org.example;

import org.example.util.DatabaseConnectionUtil;

import java.sql.Connection;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
import java.sql.PreparedStatement;
import java.sql.SQLException;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnectionUtil.getConnection()) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, "testUser");
                preparedStatement.setString(2, "testPassword");
                preparedStatement.executeUpdate();
                System.out.println("User saved successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
