package com.penguin_publications.delhi_publication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.penguin_publications.delhi_publication.Entity.Author;
import com.penguin_publications.delhi_publication.Repository.AuthorRepo;

@Service
public class AuthorService {
    
	AuthorRepo repo;
	
	public AuthorService(AuthorRepo repo)
	{
		this.repo=repo;
	}
	
	public Author addAuthor(Author author) throws Exception 
	{
		
		if(author==null)
		{
			throw new Exception("cant be null");
		}
		
		return repo.save(author);
	}
	
	public List<Author> getall() throws Exception 
	{
		
		if(!repo.findAll().isEmpty())
		{
			return repo.findAll();
		}
		
		throw new Exception ("something went wrong");
	}
}
