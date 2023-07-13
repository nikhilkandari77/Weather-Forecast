package com.example.weatherforecast.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrentWeatherDTO {
   private LocationDTO location;
   private Current current;
   private String message;
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class Current{
      private Integer temperature;
      private Integer weather_code;
      private Integer humidity;
      private Integer uv_index;
   }
}

