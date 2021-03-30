package com.piattaforme.MStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.piattaforme.MStore.entity.Costumer;
import com.piattaforme.MStore.service.CostumerService;
import com.piattaforme.MStore.util.Utils;

@CrossOrigin
@RestController
public class Subscribe {

	@Autowired
	private CostumerService service;

	@PostMapping(value = "addCostumer", consumes = "application/json", produces = "application/json")
	public String addCostumer(@RequestBody Costumer c) {
		c.setPassword(Utils.hashed(c.getPassword()));
		service.insert(c);
		return service.auth(c.getEmail(), c.getPassword());
	}

}
