package com.gov.NPCI.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gov.NPCI.classes.Movie;

@Service
public class MovieService {

	//@Service which will generally use to implement the buisness logic facade 

  @Autowired
  private Movie movie;
  
  
  public String showdetails()
  {
	  String col="180cr";
	  String name="tare jamin par";
	  double ratings=10.0;
	
	  movie.collection=col;
	  movie.name=name;
	  movie.ratings=ratings;
	  
	  if(movie.ratings>9.0)
	  {
		  return movie.toString();
	  }
	  
	  return "not good movie";
  }
  
  
  public String addmovie(Movie movie)
  {
	 List<Movie> l1= addMovies().stream().collect(Collectors.toList());
	 l1.add(movie);
	 
	 for(Movie m :l1)
	 {
		 System.out.println(m);
	 }
	 return movie.getName()+" "+"Added Successfully";
  }
  
  
//  public List<Movie> add(String name,String collection,double ratings)
//  {
//	  
//	  
//	  List<Movie> list=new ArrayList<>();
//	   
//	  List<Movie> l1=addMovies().stream().collect(Collectors.toList());
//	  
//	  list.add(new Movie(name,collection,ratings));
//	  
//	  return l1;
////	  return movie.getName()+"added successfully";
//	  
//  }
  
  
  public List<Movie> addMovies()
  {
	  List<Movie> list=Arrays.asList(new Movie("harry potter","123.4",7.5),
			  new Movie("bahubali","350cr",8.0),
			  new Movie("chak de india","230cr",7.8),
			  new Movie("batman","400cr",9.0),
			  new Movie("TJMM","450cr",8.0),
			  new Movie("border","500cr",9.5),
			  new Movie("dangal","550cr",10.0),
			  new Movie("Natsamrat","440cr",10.0));
	  return list;
  }
  
  public List<Movie> showallmovies()
  {
	  
	  return  addMovies().stream().sorted((i,j)->((int)j.ratings-(int)i.ratings)).collect(Collectors.toList());
	  
  }
  
  public Movie searchByName(String name2) throws Exception
  {
	  System.out.println("got the value from controller:"+name2);
	  
	  List<Movie> list=addMovies();
	  
	  Iterator<Movie> itr=list.iterator();
	  
	   System.out.println("searching the movie..."+name2);
	   
	   Movie m2=new Movie();
	   
	   while(itr.hasNext())
	   {
		   Movie movie=itr.next();
		   
		   if((movie.name).equalsIgnoreCase(name2))
		   {
			   System.out.println("Movie found"+name2);
			   m2=movie;
			   break;
		   }
		   else
		   {
			   m2=null;
		   }
	   }
	   
	   if(m2==null)
	   {
		   System.out.println("wrong movie name");
		   throw new Exception("Movie not found");
		   
	   }
	   
	   return m2;
  }
  
  public List<Movie> searchByRatings(double rating)
  {
	  List<Movie> list=addMovies();
	  
	  return list.stream().filter((i)->(i.ratings==rating)).collect(Collectors.toList());
//	return list;
	  
  }
  
  public List<Movie> toprated(double ratings,String name)
  {
	  List<Movie> demo=addMovies();
	  
	 return demo.stream().filter((i)->(i.ratings==ratings && (i.name).equalsIgnoreCase(name))).collect(Collectors.toList());
	  
	  
  }
}
