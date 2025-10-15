package com.CarCompany.CarCompany.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CarCompany.CarCompany.Entity.Car;
import com.CarCompany.CarCompany.Repository.CarRepo;

@Service
public class CarService {

	@Autowired
	private CarRepo repo;
	
	
	public Car AddCar( Car car)
	{
		return repo.save(car);
	}
	
	
	public List<Car> GetCar()
	{
		return repo.findAll();
	}
	
}
