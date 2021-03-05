package com.piattaforme.MStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piattaforme.MStore.entity.TestEntity;
import com.piattaforme.MStore.repository.TestRepository;

@Service
public class TestService {
	
	
	@Autowired
	TestRepository repo;
	
	public void insert(String messaggio) {
		TestEntity t = new TestEntity();
		t.setMessaggio(messaggio);
		repo.save(t);
	}

}
