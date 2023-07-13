package com.example.weatherforecast.repository;

import com.example.weatherforecast.dto.WeatherHistoryDTO;
import com.example.weatherforecast.entity.WeatherHistory;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WeatherHistoryRepo extends JpaRepository<WeatherHistory, Integer> {

    @Query("select w from WeatherHistory w where w.location.name like :name and w.date=:date" )
    Optional<WeatherHistory> findByDateAndLocationId(@Param("date")LocalDate date
            , @Param("name") String name);
    List<WeatherHistory> findAllByLocationName(String name);
    List<WeatherHistory> findAllByDate(LocalDate date);


}
