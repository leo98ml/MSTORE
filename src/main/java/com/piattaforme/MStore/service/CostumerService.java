package com.piattaforme.MStore.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.piattaforme.MStore.entity.Costumer;
import com.piattaforme.MStore.entity.Fattura;
import com.piattaforme.MStore.entity.Ordine;
import com.piattaforme.MStore.entity.Prodotto;
import com.piattaforme.MStore.repository.CostumerRepo;
import com.piattaforme.MStore.repository.FatturaRepo;
import com.piattaforme.MStore.repository.OrdineRepo;
import com.piattaforme.MStore.repository.ProdottoRepo;
import com.piattaforme.MStore.util.Utils;

@Service
public class CostumerService {

	@Autowired
	private CostumerRepo repo;

	@Autowired
	private FatturaRepo fat;
	
	@Autowired
	private ProdottoRepo prod;
	
	@Autowired
	private OrdineRepo ord;

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

	public List<Fattura> getFattureByToken(String token) {
		List<Fattura> ret = fat.findByCostumer(repo.findByToken(token).get(0));
		for (Fattura f : ret) {
			System.out.println(f.getOrdine());
		}
		return ret;
	}
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public boolean buy(String token, List<Integer> id) {
		List<Prodotto> listaProdottiDaComprare = new LinkedList<>();
		List<Integer> done = new LinkedList<>();
		Fattura fattura = new Fattura();
		Costumer cliente = repo.findByToken(token).get(0);
		fattura.setCostumer(cliente);
		GregorianCalendar oggi = new GregorianCalendar();
		fattura.setDataOrdine(oggi.getTime());
		oggi.add(GregorianCalendar.DAY_OF_MONTH, 5);
		fattura.setDataConsegna(oggi.getTime());
		double prezzo = 0;
		List<Ordine> listaOrdiniFattura = new LinkedList<>();
		for ( Integer i : id) {
			Prodotto p =prod.findById(i.longValue()).get();
			prezzo += p.getPrice()-p.getPrice()/100*p.getDiscountPercentage();
			if (!done.contains(i)) {
				try {
					if (p.getNumRisorse()==0) throw new RuntimeException();
					int num = 0;
					for (Integer j : id) {
						if (j==i) num++;
					}
					p.setNumRisorse(p.getNumRisorse()-num);
					listaProdottiDaComprare.add(p);
					done.add(i);
					prod.save(p);
					for (int k = 0 ; k < num ; k++) {
						Ordine o = new Ordine();
						o.setFattura(fattura);
						o.setNomeProdotto(p.getName());
						o.setPrezzo(p.getPrice());
						o.setScontoPercentage(p.getDiscountPercentage());
						listaOrdiniFattura.add(o);
						ord.save(o);
					}
				}
				catch(Exception e) {
					throw new RuntimeException();
				}
			}
		}
		fattura.setPrezzo(prezzo);
		fattura.setOrdine(listaOrdiniFattura);
		fat.save(fattura);
		cliente.getFattura().add(fattura);
		repo.save(cliente);
		return true;
		
	}

}
