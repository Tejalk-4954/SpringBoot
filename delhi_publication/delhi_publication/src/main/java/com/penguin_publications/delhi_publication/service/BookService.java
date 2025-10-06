package com.penguin_publications.delhi_publication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.penguin_publications.delhi_publication.Entity.Book;
import com.penguin_publications.delhi_publication.Repository.BookRepo;

@Service
public class BookService {

	@Autowired
	private BookRepo repo;
	
	
	public Book addBook(Book book) throws Exception
	{
		if(book==null)
		{
			throw new Exception("book cant be null");
		}
		
		System.out.println("recieved book"+book.getName());
		System.out.println("Savinggggg....");
		
		Book book_saved=repo.save(book);
		
		System.out.println("book Saved....");
		return book_saved;
		
	}
}
