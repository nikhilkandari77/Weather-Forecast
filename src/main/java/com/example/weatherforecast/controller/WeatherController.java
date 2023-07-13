package com.example.weatherforecast.controller;

import com.example.weatherforecast.dto.CurrentWeatherDTO;
import com.example.weatherforecast.dto.RequestDTO;
import com.example.weatherforecast.dto.WeatherHistoryDTO;
import com.example.weatherforecast.service.LocationService;
import com.example.weatherforecast.service.WeatherHistoryService;
import com.example.weatherforecast.service.serviceImpl.CurrentWeatherServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/weather")
public class WeatherController   {
    private WeatherHistoryService historyService;
    private CurrentWeatherServiceImpl weatherService;
    private LocationService locationService;

    @GetMapping("/current")
    public String getWeather( @Valid RequestDTO requestDTO, Model model)  {
        model.addAttribute("locationList",locationService.getAllLocation());
        CurrentWeatherDTO currentWeatherDTO=weatherService.getWeather(requestDTO);
        model.addAttribute("weather",currentWeatherDTO);
        return "index";
    }
    @GetMapping("/history")
    public String getHistory(@Valid RequestDTO requestDTO,Model model){
        List<WeatherHistoryDTO> weatherHistory=historyService.getWeatherHistory(requestDTO);
        model.addAttribute("historyList",weatherHistory);
        return "history";
    }
    @GetMapping("/pattern")
    public String getpattern(@Valid RequestDTO requestDTO,Model model){
        List<WeatherHistoryDTO> weatherHistory=historyService.getWeatherHistory(requestDTO);
        model.addAttribute("date",weatherHistory.stream().map(o->o.getDate())
                .collect(Collectors.toList()));
        model.addAttribute("temperature",weatherHistory
                .stream().mapToInt(o->o.getTemperature()).boxed().toList());
        return "weatherPattern";
    }


}
