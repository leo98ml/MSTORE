package com.piattaforme.MStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piattaforme.MStore.entity.Prodotto;
import com.piattaforme.MStore.service.ProdottoService;

@RestController
public class Store {
	

	@Autowired
	private ProdottoService service;
	
	@PostMapping(value = "addProdotto", consumes = {"application/json","multipart/form-data"})
	public void addProdotto(@RequestPart String p, @RequestPart List<MultipartFile> listaFile) {
		try {
			service.insert(new ObjectMapper().readValue(p, Prodotto.class),listaFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
