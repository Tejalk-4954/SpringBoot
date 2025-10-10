package com.DTO.DtoDemo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.DtoDemo.Entity.Movie;
import com.DTO.DtoDemo.Service.MovieService;

@RestController
public class MovieController {

	@Autowired
	private MovieService service;
	
	@PostMapping("/add-movie")
	public  Movie addmovie(@RequestBody Movie movie)
	{
		return service.addmovie(movie);
	}
	
	@GetMapping("/get-movie-id/{id}")
	public Movie getbyid(@PathVariable("id") int id)
	{
		return service.findbypk(id);
		
	}
}
