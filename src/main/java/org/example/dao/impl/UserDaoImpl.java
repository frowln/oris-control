package org.example.dao.impl;

import org.example.dao.UserDao;
import org.example.entity.User;
import org.example.util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final UserDao INSTANCE = new UserDaoImpl();

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private final Connection connection = DatabaseConnectionUtil.getConnection();

    private static final String SAVE_SQL = """
            INSERT INTO users (username, password)
            VALUES (?, ?)
            """;

    private static final String FIND_BY_USERNAME_SQL = """
            SELECT id, username, password
            FROM users
            WHERE username = ?
            """;

    @Override
    public void save(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USERNAME_SQL)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"))
                );
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}