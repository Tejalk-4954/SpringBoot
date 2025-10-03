package com.gov.NPCI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gov.NPCI.classes.Movie;
import com.gov.NPCI.service.MovieService;

@RestController
@CrossOrigin(allowedHeaders ="*")
public class MovieController {

	//controller should not be bulky, controller has 15 to 20 lines only note more that
//	@Autowired
//	private Movie movie;
	
	@Autowired
	private MovieService service;
	
	@GetMapping("/get-movie")
	public String getdet()
	{
//		movie.collection="345.4 cr";
//		movie.name="ramayan";
//		movie.ratings=8.0;
//		
//		return movie.toString();
		return service.showdetails();
	}
	
	@GetMapping("/get-all")
	public List<Movie> getall()
	{
		return service.showallmovies();
	}
	
	@PostMapping("/find-movie")
	public Movie findmovie(@RequestParam("name2") String name2) throws Exception 
	{
		/**@RequestParam is used to accept the value from postman client/user
		*  it make static URL 
		*/
		
		return service.searchByName("DDLJ");
	}
	
	@PostMapping("/find-movie-variable/{var}")
	public Movie find(@PathVariable("var") String var) throws Exception
	{
		
		/**
		 * Annotation which indicates that a method parameter should be bound to a URI template variable
		 *  make dynamic URL
		 */
		
		return service.searchByName(var);
	}
	
	@PostMapping("/find-by-ratings/{ratin}")
	public List<Movie> findbyRatings(@PathVariable("ratin") double ratin)
	{
		return service.searchByRatings(ratin);
		
	}
	
	
	@PostMapping("/dore/{name}/{ratings}")
	public List<Movie> dore(@PathVariable("rating") double rating,@PathVariable("name") String name)
	{
         return service.toprated(rating, name);		
	}
	
	@PostMapping("/add-movie")
	public String addOne(@RequestBody Movie movie)
	{
		return service.addmovie(movie);
	}
}
