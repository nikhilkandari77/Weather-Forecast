package com.example.weatherforecast.controller;

import com.example.weatherforecast.dto.CurrentWeatherDTO;
import com.example.weatherforecast.dto.RequestDTO;
import com.example.weatherforecast.service.LocationService;
import com.example.weatherforecast.service.serviceImpl.CurrentWeatherServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {
    private CurrentWeatherServiceImpl weatherService;
    private LocationService locationService;
    @GetMapping("/")
    public String home(Model model) {
        CurrentWeatherDTO currentWeatherDTO=weatherService.getWeather(new RequestDTO("new delhi"));
        model.addAttribute("weather",currentWeatherDTO);
        model.addAttribute("locationList",locationService.getAllLocation());
        return "index";
    }


}
