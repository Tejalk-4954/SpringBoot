package com.penguin_publications.delhi_publication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Book {

	@Id
	private int id;
	
	
    private String name;	
	
    
    private double price;
    
    
    private double ratings;


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
