package com.penguin_publications.delhi_publication.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Author {
	
	
	public Author() {
		super();
	}

	public Author( String authorName, String email) {
		super();
		this.authorName = authorName;
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int authorId;
	
	@Column(nullable=false)
	private String authorName;
	
	private String email;
	
	
	@OneToMany
	@JoinColumn
	private List<Book> books;

	public int getAuthorId() {
		return authorId;
	}

	

	public Author(int authorId, String authorName, String email, List<Book> books) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.email = email;
		this.books = books;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}