package com.piattaforme.MStore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piattaforme.MStore.entity.Costumer;
import com.piattaforme.MStore.entity.Fattura;
import com.piattaforme.MStore.service.CostumerService;

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
	
	@GetMapping(value = "getFattureByToken", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Fattura> getFattureByToken(@RequestParam String token){
		List<Fattura> ret = service.getFattureByToken(token);
		ret.stream().forEach((f)->{ 
			f.setCostumer(null);
			f.getOrdine().stream().forEach((o)->{
				o.setFattura(null);
			});
		});
		return ret;
	}
	
	@GetMapping(value = "buy", produces = {MediaType.APPLICATION_JSON_VALUE})
	public boolean buy(@RequestParam String token,@RequestParam List<Integer> id){
		return service.buy(token,id);
	}
}
