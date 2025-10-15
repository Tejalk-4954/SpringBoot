package com.CarCompany.CarCompany.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CarCompany.CarCompany.Entity.Experience;
import com.CarCompany.CarCompany.Repository.ExperienceRepo;

@Service
public class ExperienceService {

	@Autowired
	private ExperienceRepo repo;
	
	public Experience addexperience(Experience ex)
	{
		return repo.save(ex);
	}
	
	
}
