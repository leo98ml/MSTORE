package com.piattaforme.MStore.controller;

import java.util.Map;

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
public class Profilo {
	
	@Autowired
	private CostumerService service;

	@PostMapping(value = "getUserByToken", consumes = "application/json", produces = "application/json")
	public Costumer getUserByToken(@RequestBody Map<String,String> mappaParametri) {
		Costumer c = service.findByToken(mappaParametri.get("token"));
		c.setPassword("");
		c.setToken("");
		return c;
	}
}
