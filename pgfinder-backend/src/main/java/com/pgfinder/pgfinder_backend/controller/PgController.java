package com.pgfinder.pgfinder_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pgfinder.pgfinder_backend.Entity.Pg;
import com.pgfinder.pgfinder_backend.repository.PgRepository;

@RestController
@RequestMapping("/api/pgs")
public class PgController {
    @Autowired
    private PgRepository pgRepository;

    @GetMapping("/nearby")
    public List<Pg> getNearbyPgs(@RequestParam double lat, @RequestParam double lon, @RequestParam double radius) {
        double latMin = lat - radius / 111; // Approx km to degrees
        double latMax = lat + radius / 111;
        double lonMin = lon - radius / (111 * Math.cos(Math.toRadians(lat)));
        double lonMax = lon + radius / (111 * Math.cos(Math.toRadians(lat)));
        return pgRepository.findByLatitudeBetweenAndLongitudeBetween(latMin, latMax, lonMin, lonMax);
    }

    @PostMapping
    public Pg createPg(@RequestBody Pg pg) {
        return pgRepository.save(pg);
    }
}