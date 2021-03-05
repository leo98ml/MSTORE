package com.piattaforme.MStore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.piattaforme.MStore.entity.Fattura;

@Repository
public interface FatturaRepo extends CrudRepository<Fattura, Long>{

}
