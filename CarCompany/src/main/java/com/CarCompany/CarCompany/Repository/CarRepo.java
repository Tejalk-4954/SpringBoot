package com.CarCompany.CarCompany.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CarCompany.CarCompany.Entity.Car;

public interface CarRepo extends JpaRepository<Car, Integer> {

}
