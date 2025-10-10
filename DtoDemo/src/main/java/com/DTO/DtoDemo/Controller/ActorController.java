package com.DTO.DtoDemo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.DtoDemo.DTO.ActorDto;
import com.DTO.DtoDemo.Entity.Actor;
import com.DTO.DtoDemo.Service.ActorService;

@RestController
public class ActorController {

	@Autowired
	private ActorService service;
	
	
	@PostMapping("/add-actor")
	public Actor addActor(@RequestBody Actor act)
	{
		return service.addActor(act);
	}
	
	
	@GetMapping("/get-actor-id/{id}")
	public ActorDto getbyId(@PathVariable("id") int id)
	{
		return service.getbyIdact(id);
	}
	
	
}
