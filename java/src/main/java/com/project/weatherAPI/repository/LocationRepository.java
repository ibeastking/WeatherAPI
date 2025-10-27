package com.project.weatherAPI.repository;

import com.project.weatherAPI.entity.Location;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface LocationRepository extends JpaRepository<Location, String> {
}
