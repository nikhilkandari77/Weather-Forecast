package com.example.weatherforecast.service.serviceImpl;

import com.example.weatherforecast.dto.CurrentWeatherDTO;
import com.example.weatherforecast.dto.RequestDTO;
import com.example.weatherforecast.dto.ResponseDTO;
import com.example.weatherforecast.entity.WeatherHistory;
import com.example.weatherforecast.helper.Constant;
import com.example.weatherforecast.repository.WeatherHistoryRepo;
import com.example.weatherforecast.service.CurrentWeatherService;
import jakarta.persistence.Temporal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.http.HttpRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CurrentWeatherServiceImpl implements CurrentWeatherService {
    private WebClient webClient;
    private WeatherHistoryRepo historyRepo;

    public Flux<CurrentWeatherDTO> requestAPI(RequestDTO requestDTO){
        return webClient.get()
                .uri("current?access_key="+Constant.access_key+"&query="+requestDTO.getQuery())
                .retrieve().bodyToFlux(CurrentWeatherDTO.class)
                .doOnError(throwable -> new Exception("error in api call"));
    }
    @Override
    public CurrentWeatherDTO getWeather(RequestDTO requestDTO)  {
        Flux<CurrentWeatherDTO> flux=requestAPI(requestDTO);

        CurrentWeatherDTO currentWeatherDTO=flux.blockFirst();


        Optional<WeatherHistory>weatherHistory=historyRepo.findByDateAndLocationId(
                LocalDate.now(),currentWeatherDTO.getLocation().getName());
        if(weatherHistory.isPresent())
             currentWeatherDTO.setMessage(weatherHistory.get().getMessage());
        else
            currentWeatherDTO.setMessage("NA");

        return currentWeatherDTO;
    }

}
