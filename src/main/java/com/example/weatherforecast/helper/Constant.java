package com.example.weatherforecast.helper;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public abstract class Constant {

     public final static String baseUrl="http://api.weatherstack.com/";
     public final static String access_key="ab24ab2f0a4d1b1c6e0355ced3abacd6";
}
