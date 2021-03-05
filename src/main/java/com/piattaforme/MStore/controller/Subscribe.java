package com.piattaforme.MStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.piattaforme.MStore.entity.Costumer;
import com.piattaforme.MStore.service.CostumerService;

@RestController
public class Subscribe {
	
	@Autowired
	private CostumerService service;
	
	@PostMapping(value = "addCostumer", consumes = "application/json")
	public void addCostumer(@RequestBody Costumer c) {
		service.insert(c);
	}
	

}
