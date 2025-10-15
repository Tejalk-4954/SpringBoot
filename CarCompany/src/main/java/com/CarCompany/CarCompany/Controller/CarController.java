package com.CarCompany.CarCompany.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CarCompany.CarCompany.Entity.Car;
import com.CarCompany.CarCompany.Service.CarService;

@RestController
public class CarController {

	@Autowired
	private CarService service;
	
	@PostMapping("/add-car")
	public Car Add(@RequestBody Car car)
	{
		return service.AddCar(car);
	}
	
	@GetMapping("/get-all")
	public List<Car> Getcar()
	{
		return service.GetCar();
		
	}
}
