package com.piattaforme.MStore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.piattaforme.MStore.entity.Prodotto;

@Repository
public interface ProdottoRepo extends CrudRepository<Prodotto, Long> {

}
