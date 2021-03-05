package com.piattaforme.MStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piattaforme.MStore.entity.Costumer;
import com.piattaforme.MStore.repository.CostumerRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CostumerService {

	@Autowired
	private CostumerRepo repo;

	public void insert(String name, String surname, 
			String indirizzoSpedizione, String email, 
			String phone, String password) {
		repo.save(Costumer.builder().name(name).surname(surname)
		.indirizzoSpedizione(indirizzoSpedizione).email(email).phone(phone)
		.password(password).build());
	}

	public void insert(Costumer c) {
		repo.save(c);
	}

}
