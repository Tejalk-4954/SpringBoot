package com.penguin_publications.delhi_publication.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.penguin_publications.delhi_publication.Entity.Author;
import com.penguin_publications.delhi_publication.service.AuthorService;

@RequestMapping("/author-controller")
@RestController
public class AuthorController {

	AuthorService service;
	
	public AuthorController(AuthorService service)
	{
		this.service=service;
	}
	
	
	@PostMapping("/add-author")
	public Author add(@RequestBody  Author author) throws Exception
	{
		 System.out.println(author.getAuthorName());
		 return service.addAuthor(author);
	}
	
	
	@GetMapping("/get-all-authors")
	public List<Author> getall() throws Exception
	{
		return service.getall();
	}
}
