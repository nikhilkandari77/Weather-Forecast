package com.example.weatherforecast.service.serviceImpl;

import com.example.weatherforecast.dto.LocationDTO;
import com.example.weatherforecast.repository.LocationRepo;
import com.example.weatherforecast.service.LocationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {
    private LocationRepo locationRepo;
    private ModelMapper mapper;
    @Override
    public List<LocationDTO> getAllLocation() {
        return locationRepo.findAll().stream()
                .map(location -> mapper.map(location,LocationDTO.class)).collect(Collectors.toList());
    }
}
