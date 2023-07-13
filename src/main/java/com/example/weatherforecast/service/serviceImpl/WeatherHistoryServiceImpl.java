package com.example.weatherforecast.service.serviceImpl;

import com.example.weatherforecast.dto.RequestDTO;
import com.example.weatherforecast.dto.WeatherHistoryDTO;
import com.example.weatherforecast.entity.User;
import com.example.weatherforecast.entity.WeatherHistory;
import com.example.weatherforecast.repository.UserRepo;
import com.example.weatherforecast.repository.WeatherHistoryRepo;
import com.example.weatherforecast.service.WeatherHistoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WeatherHistoryServiceImpl implements WeatherHistoryService {
    private WeatherHistoryRepo historyRepo;
    private ModelMapper mapper;
    private UserRepo userRepo;

    @Override
    public List<WeatherHistoryDTO> getWeatherHistory(RequestDTO requestDTO) {
        return historyRepo.findAllByLocationName(requestDTO.getQuery()).stream()
                    .map(weatherHistory -> mapper.map(weatherHistory,WeatherHistoryDTO.class))
                    .collect(Collectors.toList());

    }

    @Override
    public List<WeatherHistoryDTO> getAllByDate(RequestDTO requestDTO) {
        return historyRepo.findAllByDate(requestDTO.getDate()).stream()
                .map(weatherHistory -> mapper.map(weatherHistory,WeatherHistoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void postWeatherHistory(RequestDTO requestDTO, String username) {
        WeatherHistory weatherHistory=historyRepo.findById(requestDTO.getId()).get();
        if(weatherHistory.getMessage()==null) {
            System.out.println(username);
            User user = userRepo.findByEmail(username);
            weatherHistory.setMessage(requestDTO.getMessage());
            user.getWeatherUpdates().add(weatherHistory);
            weatherHistory.setUser(user);
            userRepo.save(user);
        }
        else{
            weatherHistory.setMessage(requestDTO.getMessage());
            historyRepo.save(weatherHistory);
        }
    }




}
