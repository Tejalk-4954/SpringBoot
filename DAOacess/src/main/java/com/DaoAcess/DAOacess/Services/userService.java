package com.DaoAcess.DAOacess.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.DaoAcess.DAOacess.Entity.userEntity;
import com.DaoAcess.DAOacess.Repository.userRepo;

public class userService implements userServiceInterface{

	@Autowired
	private userRepo repo;
	
	public userEntity addUser(userEntity entity)
	{
		return repo.save(entity);
	}
	
	public List<userEntity> getallUsers()
	{
		return repo.findAll();
	}
}
