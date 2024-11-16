package org.example.dao.impl;

import org.example.util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginAttemptDaoImpl {
    private static final LoginAttemptDaoImpl INSTANCE = new LoginAttemptDaoImpl();

    public static LoginAttemptDaoImpl getInstance() {
        return INSTANCE;
    }

    private final Connection connection = DatabaseConnectionUtil.getConnection();

    private static final String SAVE_LOGIN_ATTEMPT_SQL = """
            INSERT INTO login_attempts (username, success)
            VALUES (?, ?)
            """;

    public void saveLoginAttempt(String username, boolean success) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_LOGIN_ATTEMPT_SQL)) {
            preparedStatement.setString(1, username);
            preparedStatement.setBoolean(2, success);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving login attempt", e);
        }
    }
}
