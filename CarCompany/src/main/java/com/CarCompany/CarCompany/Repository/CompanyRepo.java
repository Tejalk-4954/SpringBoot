package com.CarCompany.CarCompany.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CarCompany.CarCompany.Entity.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

}
