package com.CarCompany.CarCompany.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CarCompany.CarCompany.Entity.CEO;
import com.CarCompany.CarCompany.Repository.CEORepo;

@Service
public class CEOService {

	@Autowired
	private CEORepo crepo;
	
	public CEO addCeo(CEO ceo)
	{
		return crepo.save(ceo);
	}
	
	public List<CEO> getCeo()
	{
		return crepo.findAll();
	}
	
	
}
