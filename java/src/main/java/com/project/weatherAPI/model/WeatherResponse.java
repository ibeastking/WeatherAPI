package com.project.weatherAPI.model;

import com.project.weatherAPI.entity.Location;
import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class WeatherResponse {
    public Location location;
}
