package com.example.weatherforecast.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    private String message;
    private Integer temperature;
    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Location location;

    @PrePersist
    public void setDate(){
        date=LocalDate.now();
    }
}
