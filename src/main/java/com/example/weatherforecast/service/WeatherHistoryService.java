package com.example.weatherforecast.service;

import com.example.weatherforecast.dto.RequestDTO;
import com.example.weatherforecast.dto.WeatherHistoryDTO;
import com.example.weatherforecast.entity.WeatherHistory;
import com.example.weatherforecast.repository.WeatherHistoryRepo;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.security.Principal;
import java.util.List;

public interface WeatherHistoryService {
    List<WeatherHistoryDTO> getWeatherHistory(RequestDTO requestDTO);

    List<WeatherHistoryDTO> getAllByDate(RequestDTO requestDTO);


    void postWeatherHistory(RequestDTO requestDTO, String username);

}
