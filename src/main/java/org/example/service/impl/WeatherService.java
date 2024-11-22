package org.example.service.impl;

import org.example.dao.impl.WeatherDaoImpl;
import org.example.entity.Weather;

public class WeatherService {
    private final WeatherDaoImpl weatherDao = WeatherDaoImpl.getInstance();
    private static final WeatherService instance = new WeatherService();

    public static WeatherService getInstance() {
        return instance;
    }

    public void saveWeather(Weather weather) {
        weatherDao.saveWeather(weather);
    }
}
