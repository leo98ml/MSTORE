package com.piattaforme.MStore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.piattaforme.MStore.service.CostumerService;
import com.piattaforme.MStore.util.Utils;

@CrossOrigin
@RestController
public class Login {
	

	@Autowired
	private CostumerService service;
	
	@PostMapping(value = "login", consumes = "application/json", produces = "application/json")
	public String login(@RequestBody Map<String,String> mappaParametri) {
		return service.auth(mappaParametri.get("email"),Utils.hashed(mappaParametri.get("password")));
	}
	
	@PostMapping(value = "isSessionValid", consumes = "application/json", produces = "application/json")
	public boolean isSessionValid(@RequestBody Map<String,String> mappaParametri) {
		String token = mappaParametri.get("token");
		if (token == null) return false;
		return service.isSessionValid(token);
	}
	
}
