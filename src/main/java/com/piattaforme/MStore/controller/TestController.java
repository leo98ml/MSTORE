package com.piattaforme.MStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piattaforme.MStore.model.TestReturnType;
import com.piattaforme.MStore.service.TestService;

@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
	TestService service;
	
	@GetMapping("ciao")
	public TestReturnType ciao (@RequestParam(name="nome") String nome) {
		TestReturnType ret = new TestReturnType();
		ret.setMessage(nome);
		service.insert(ret.getMessage());
		return ret;
	}
	
	

}
