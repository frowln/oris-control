package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.entity.Weather;
import org.example.http.HttpClient;
import org.example.service.impl.WeatherService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String API_KEY = "bd5e378503939ddaee76f12ad7a97608";
    private final HttpClient httpClient = new HttpClient();
    private final WeatherService weatherService = WeatherService.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("profile.html").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("{\"error\":\"Unauthorized\"}");
            return;
        }

        String city = req.getParameter("city");
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("q", city);
        queryParams.put("appid", API_KEY);
        queryParams.put("units", "metric");
        queryParams.put("lang", "ru");

        try {
            String response = httpClient.get(API_URL, new HashMap<>(), queryParams);

            Map<String, Object> weatherData = objectMapper.readValue(response, Map.class);

            Map<String, Object> main = (Map<String, Object>) weatherData.get("main");
            Map<String, Object> weather = ((List<Map<String, Object>>) weatherData.get("weather")).get(0);

            double temperature = (double) main.get("temp");
            String condition = (String) weather.get("description");

            Weather savedWeather = new Weather(0, city, temperature, condition, userId);
            weatherService.saveWeather(savedWeather);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("city", city);
            responseMap.put("temperature", temperature);
            responseMap.put("condition", condition);

            resp.setContentType("application/json");
            resp.getWriter().write(objectMapper.writeValueAsString(responseMap));

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"Ошибка: " + e.getMessage() + "\"}");
        }
    }
}
