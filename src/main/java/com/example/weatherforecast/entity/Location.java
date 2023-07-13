package com.example.weatherforecast.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String country;
    private String region;
    private double lat;
    private double lon;
    @OneToMany(mappedBy="location",fetch =FetchType.EAGER)
    private List<WeatherHistory> weatherUpdates;
}
