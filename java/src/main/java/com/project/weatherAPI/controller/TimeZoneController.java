package com.project.weatherAPI.controller;

import com.project.weatherAPI.entity.Location;
import com.project.weatherAPI.model.WeatherResponse;
import com.project.weatherAPI.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@Slf4j
@RequestMapping("/tz")
public class TimeZoneController {
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    LocationRepository locationRepository;

    @GetMapping("/getName")
    public String getName(@RequestParam String q,
                          @RequestParam String key) {
        log.info("Calling Rest template");
        String url = "https://api.weatherapi.com/v1/timezone.json";

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("q", q)
                .queryParam("key", key);

        ResponseEntity<WeatherResponse> response;

        try{
            response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, WeatherResponse.class);
            log.info("Rest template Response: {}", response);
            log.info("Response Status Code: {}", response.getStatusCode());
        } catch(HttpClientErrorException ex) {
            log.info("Exception: {}", ex.toString());
            Matcher matcher = Pattern.compile("\"message\":\"([^\"]+)\"").matcher(ex.getResponseBodyAsString());
            return matcher.find() ? matcher.group(1) : "Unknown error";
        }

        Location location = Optional.ofNullable(response.getBody()).map(WeatherResponse::getLocation).orElse(null);

        if(location == null) return "Wrong Pin code";

        log.info("Inserting Location: {} into database", location);
        locationRepository.save(location);
        log.info("Insertion Complete");

        return location.getName();
    }
}
