package org.example.dao.impl;

import org.example.entity.Weather;
import org.example.util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WeatherDaoImpl {
    public static final WeatherDaoImpl INSTANCE = new WeatherDaoImpl();
    public static WeatherDaoImpl getInstance() {
        return INSTANCE;
    }

    private WeatherDaoImpl() {}

    private static final String INSERT_WEATHER_LOG_SQL = """
        INSERT INTO weather (city, temperature, condition, user_id)
        VALUES (?, ?, ?, ?)
    """;

    public void saveWeather(Weather weather) {
        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WEATHER_LOG_SQL)) {

            preparedStatement.setString(1, weather.getCity());
            preparedStatement.setDouble(2, weather.getTemperature());
            preparedStatement.setString(3, weather.getCondition());
            preparedStatement.setInt(4, weather.getUserId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save weather log", e);
        }
    }
}
