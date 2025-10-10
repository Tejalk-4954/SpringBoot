package com.DTO.DtoDemo.Service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DTO.DtoDemo.DTO.ActorDto;
import com.DTO.DtoDemo.DTO.MovieDto;
import com.DTO.DtoDemo.Entity.Actor;
import com.DTO.DtoDemo.Entity.Movie;
import com.DTO.DtoDemo.Repository.ActorRepo;


@Service
public class ActorService {

	@Autowired
	private ActorRepo repo;
	
	public Actor addActor(Actor actor)
	{
		return repo.save(actor);
	}
	
	
	public ActorDto getbyIdact(int id)
	{
		return  (converter(repo.findById(id).get()));
	}
	
	
	public static ActorDto converter(Actor act)
	{
		ActorDto dto=new ActorDto();
		
		dto.setAname(act.getAname());
		
		dto.setMovies(act.getMovies().stream().map(ActorService::con2).collect(Collectors.toList()));
	
	     return dto;
	}
	
	
	public static MovieDto con2(Movie movie)
	{
		MovieDto dto=new MovieDto();
		
		dto.setName(movie.getName());
		
		return dto;
		
	}
	
}
