package com.penguin_publications.delhi_publication.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.penguin_publications.delhi_publication.Entity.Book;
import com.penguin_publications.delhi_publication.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService service;
	
	
	@PostMapping("/add-book")
	public Book addbookController(@RequestBody Book book) throws Exception 
	{
		return service.addBook(book);
	}
}
