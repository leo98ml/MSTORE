package com.piattaforme.MStore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.piattaforme.MStore.entity.Costumer;


@Repository
public interface CostumerRepo extends CrudRepository<Costumer,Long>{
	
	public List<Costumer> findByEmailAndPassword(String email,String password);

	public List<Costumer> findByToken(String token);
}
