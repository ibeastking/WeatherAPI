package com.project.weatherAPI.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "History")
public class Location {
    @Id
    String name;
    String region;
    String country;
    String lat;
    String lon;
    String tz_id;
    String localtime_epoch;
    String local_time;
}
