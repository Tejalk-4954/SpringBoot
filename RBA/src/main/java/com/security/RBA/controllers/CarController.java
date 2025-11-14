package com.security.RBA.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

	@GetMapping("/get-cars")
	public ResponseEntity<String> getcars()
	{
		return ResponseEntity.status(HttpStatus.OK).body("All cars fetched...");
		
	}
	
	@PostMapping("/add-cars")
	public ResponseEntity<String> addcar(@RequestParam String carname)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body("the car added is:"+carname);
	}
}
