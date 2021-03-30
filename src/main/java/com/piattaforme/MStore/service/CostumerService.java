package com.piattaforme.MStore.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piattaforme.MStore.entity.Costumer;
import com.piattaforme.MStore.repository.CostumerRepo;
import com.piattaforme.MStore.util.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CostumerService {

	@Autowired
	private CostumerRepo repo;

	public void insert(String name, String surname, String indirizzoSpedizione, String email, String phone,
			String password) {
		repo.save(Costumer.builder().name(name).surname(surname).indirizzoSpedizione(indirizzoSpedizione).email(email)
				.phone(phone).password(password).build());
	}

	public void insert(Costumer c) {
		repo.save(c);
	}

	public String auth(String email, String hashed) {
		List<Costumer> l = repo.findByEmailAndPassword(email, hashed);
		if (l.size() == 0)
			return null;
		Costumer c = l.get(0);
		GregorianCalendar oggi = new GregorianCalendar();
		oggi.add(GregorianCalendar.DAY_OF_MONTH, 10);
		String token = Utils.hashed(("" + Math.random() + c.getEmail() + Math.random()+ System.currentTimeMillis() + Math.random()))
				+new SimpleDateFormat("dd/MM/yyyy").format(oggi.getTime());
		c.setToken(token);
		repo.save(c);
		
		return token;
	}

	public boolean isSessionValid(String token) {
		try {
			if (new SimpleDateFormat("dd/MM/yyyy").parse(token.substring(token.length()-10)).compareTo(new GregorianCalendar().getTime())<=0) return false;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Costumer> l = repo.findByToken(token);
		return l.size()>0;
	}

	public Costumer findByToken(String token) {
		List<Costumer> l = repo.findByToken(token);
		return l.get(0);
	}

}
