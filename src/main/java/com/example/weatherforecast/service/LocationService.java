package com.example.weatherforecast.service;

import com.example.weatherforecast.dto.LocationDTO;

import java.util.List;

public interface LocationService {
    List<LocationDTO> getAllLocation();
}
