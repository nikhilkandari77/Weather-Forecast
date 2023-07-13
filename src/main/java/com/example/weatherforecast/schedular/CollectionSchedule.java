package com.example.weatherforecast.schedular;

import com.example.weatherforecast.dto.CurrentWeatherDTO;
import com.example.weatherforecast.dto.LocationDTO;
import com.example.weatherforecast.dto.RequestDTO;
import com.example.weatherforecast.entity.WeatherHistory;
import com.example.weatherforecast.repository.LocationRepo;
import com.example.weatherforecast.repository.WeatherHistoryRepo;
import com.example.weatherforecast.service.serviceImpl.CurrentWeatherServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@EnableScheduling
@AllArgsConstructor
public class CollectionSchedule {
    private CurrentWeatherServiceImpl weatherService;
    private LocationRepo locationRepo;
    private WeatherHistoryRepo historyRepo;
    private ModelMapper mapper;
    //@Scheduled(fixedDelay = 30000)
    public void collectWeatherData(){
        List<LocationDTO> locationList=locationRepo.findAll().stream()
                .map(location -> mapper.map(location,LocationDTO.class)).collect(Collectors.toList());
        System.out.println(locationList);
        List<WeatherHistory> weatherHistoryList=locationList.stream()
                .map(locationDTO->{
                    CurrentWeatherDTO dto= weatherService.requestAPI(new RequestDTO(locationDTO.getName())).blockFirst();
                    assert dto != null;
                    dto.getLocation().setId(locationDTO.getId());
                    return dto;
                })
                .map(currentWeatherDTO -> {
                           WeatherHistory history= mapper.map(currentWeatherDTO, WeatherHistory.class);
                           history.setTemperature(currentWeatherDTO.getCurrent().getTemperature());
                           history.getLocation().setWeatherUpdates(List.of(history));
                           return history;
                        }
                )
                .collect(Collectors.toList());
                historyRepo.saveAll(weatherHistoryList);

    }
}
