package com.derezhenko.net.dto;

public class InfoDto {
    private String city;
    private String temperature;
    private String humidity;
    private String description;

    public InfoDto(String city, String temperature, String humidity, String description) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getDescription() {
        return description;
    }
}
