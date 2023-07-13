package com.example.weatherforecast.dto;

import com.example.weatherforecast.entity.Location;
import com.example.weatherforecast.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherHistoryDTO {
    private Integer id;
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    private String message;
    private Integer temperature;
    private LocationDTO location;
}
