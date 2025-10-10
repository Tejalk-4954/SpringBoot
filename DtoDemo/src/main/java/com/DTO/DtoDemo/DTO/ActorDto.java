package com.DTO.DtoDemo.DTO;

import java.util.List;

public class ActorDto {

	private String Aname;
	
	List<MovieDto> movies;

	public String getAname() {
		return Aname;
	}

	public void setAname(String aname) {
		Aname = aname;
	}

	public List<MovieDto> getMovies() {
		return movies;
	}

	public void setMovies(List<MovieDto> movies) {
		this.movies = movies;
	}
	
	
}
