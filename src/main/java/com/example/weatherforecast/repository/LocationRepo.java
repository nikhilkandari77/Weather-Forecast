package com.example.weatherforecast.repository;

import com.example.weatherforecast.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location,Integer> {
}
