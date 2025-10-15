package com.CarCompany.CarCompany.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CarCompany.CarCompany.Entity.CEO;

public interface CEORepo extends JpaRepository<CEO, Integer> {

}
