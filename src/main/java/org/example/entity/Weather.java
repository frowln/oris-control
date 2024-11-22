package org.example.entity;

public class Weather {
    private int id;
    private String city;
    private double temperature;
    private String condition;
    private int userId;

    public Weather(int id, String city, double temperature, String condition, int userId) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
        this.condition = condition;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
