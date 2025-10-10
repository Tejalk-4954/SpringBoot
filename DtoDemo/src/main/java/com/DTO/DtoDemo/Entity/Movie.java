package com.DTO.DtoDemo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int id;
   
   @Column(nullable=false)
   private String name;
   
   private double mCollection;

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

public double getmCollection() {
	return mCollection;
}

public void setmCollection(double mCollection) {
	this.mCollection = mCollection;
}
   
   
}
