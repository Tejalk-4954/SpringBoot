package com.DTO.DtoDemo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DTO.DtoDemo.Entity.Movie;
import com.DTO.DtoDemo.Repository.MovieRepo;



@Service
public class MovieService {

	@Autowired
	private MovieRepo repo;
	
	public Movie addmovie(Movie movie)
	{
		
		return repo.save(movie);
	}
	
	

	public Movie findbypk(int id)
	{
		return repo.findById(id).get();
	}
}
