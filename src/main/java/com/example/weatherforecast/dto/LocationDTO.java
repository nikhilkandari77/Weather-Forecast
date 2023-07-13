package com.example.weatherforecast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDTO {
    private Integer id;
    private String name;
    private String country;
    private String region;
    private String lat;
    private String lon;
    private Long localtime_epoch;
    private ZoneId timezone_id;
}
