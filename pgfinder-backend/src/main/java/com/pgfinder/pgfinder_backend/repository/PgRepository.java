package com.pgfinder.pgfinder_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgfinder.pgfinder_backend.Entity.Pg;

public interface PgRepository extends JpaRepository<Pg, Long> {

	List<Pg> findByLatitudeBetweenAndLongitudeBetween(double latMin, double latMax, double lonMin, double lonMax);
}
