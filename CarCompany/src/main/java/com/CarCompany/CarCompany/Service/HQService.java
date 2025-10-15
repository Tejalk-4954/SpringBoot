package com.CarCompany.CarCompany.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CarCompany.CarCompany.Entity.HQ;
import com.CarCompany.CarCompany.Repository.HQRepo;

@Service
public class HQService {

	@Autowired
	private HQRepo repo;
	
	public HQ addHq(HQ hq)
	{
		return repo.save(hq);
	}
	
	public List<HQ> getHQ()
	{
		return repo.findAll();
	}
}
