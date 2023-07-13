package com.example.weatherforecast.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private String query;
    private LocalDate date;
    private Integer id;
    private String message;

    public RequestDTO(String city) {
        query=city;
    }

    public RequestDTO(Integer id) {
        this.id=id;
    }
    public RequestDTO(LocalDate date) {
        this.date=date;
    }
}
