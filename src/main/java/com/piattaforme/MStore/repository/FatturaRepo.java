package com.piattaforme.MStore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.piattaforme.MStore.entity.Costumer;
import com.piattaforme.MStore.entity.Fattura;

@Repository
public interface FatturaRepo extends CrudRepository<Fattura, Long>{

	List<Fattura> findByCostumer(Costumer c);

}
