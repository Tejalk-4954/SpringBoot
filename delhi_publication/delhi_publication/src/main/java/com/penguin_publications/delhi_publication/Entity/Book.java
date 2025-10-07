package com.penguin_publications.delhi_publication.Entity;

//import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class Book {

	@Id
	private int id;
    private String name;	
    private double price;
    private double ratings;
    
    
    @JoinColumn
    @OneToOne
    private Author author;
    
   
    
    

	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}


	public Book(int id, String name, double price, double ratings) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.ratings = ratings;
	}


	public Book() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public double getRatings() {
		return ratings;
	}


	public void setRatings(double ratings) {
		this.ratings = ratings;
	}
	
	
}
