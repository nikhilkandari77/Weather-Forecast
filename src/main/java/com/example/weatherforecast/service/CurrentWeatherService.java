package com.example.weatherforecast.service;

import com.example.weatherforecast.dto.CurrentWeatherDTO;
import com.example.weatherforecast.dto.RequestDTO;

import java.text.ParseException;

public interface CurrentWeatherService {
    CurrentWeatherDTO getWeather(RequestDTO requestDTO) throws ParseException;
}
