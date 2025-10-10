package com.DTO.DtoDemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DTO.DtoDemo.Entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer> {

}
