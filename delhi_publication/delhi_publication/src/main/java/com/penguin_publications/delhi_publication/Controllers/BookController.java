package com.penguin_publications.delhi_publication.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.penguin_publications.delhi_publication.Entity.Book;
import com.penguin_publications.delhi_publication.service.BookService;


@RequestMapping("/book-controller")
@RestController
@CrossOrigin(allowedHeaders ="*")
public class BookController {

	@Autowired
	private BookService service;
	
	
	@PostMapping("/add-book")
	public Book addbookController(@RequestBody Book book) throws Exception 
	{
		return service.addBook(book);
	}
	
	
	@GetMapping("/get-all")
	public List<Book> getall(){
		return service.getall();
	}
	
	@PutMapping("/update-book")
	public String updatebook()
	{
		return "updated the url....";
	}
	
	@DeleteMapping("/delete-book")
	public String deleteBook()
	{
		return "deleted...";
	}
}
