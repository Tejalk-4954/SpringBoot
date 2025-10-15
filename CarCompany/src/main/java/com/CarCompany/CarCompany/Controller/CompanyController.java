package com.CarCompany.CarCompany.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CarCompany.CarCompany.Entity.Company;
import com.CarCompany.CarCompany.Service.CompanyService;

@RestController
public class CompanyController {

	@Autowired
	private CompanyService service;
	
	
	@PostMapping("/add-company")
	public Company Addcompany(@RequestBody Company company)
	{
		return service.AddComp(company);
	}
}
