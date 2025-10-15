package com.CarCompany.CarCompany.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CarCompany.CarCompany.Entity.Company;
import com.CarCompany.CarCompany.Repository.CompanyRepo;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepo repo;
	
	
	public Company AddComp(Company company)
	{
		return repo.save(company);
	}
	
	public List<Company> getComp()
	{
		return repo.findAll();
	}
}
