package com.DaoAcess.DAOacess.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.DaoAcess.DAOacess.Entity.userEntity;
import com.DaoAcess.DAOacess.Services.userService;

@RestController
public class userController {

	
	@Autowired
	private userService service;
	

	@PostMapping("/add-user")
	public userEntity user(@RequestBody userEntity entity)
	{
		return service.addUser(entity);
	}
	
	@GetMapping("/add-user")
	public List<userEntity> getusers()
	{
		return service.getallUsers();
		
	}
	
}
