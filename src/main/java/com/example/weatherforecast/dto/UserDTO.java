package com.example.weatherforecast.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String contact;
    private String password;
    private String email;
    private String role;
}
