package com.piattaforme.MStore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.piattaforme.MStore.entity.Ordine;

@Repository
public interface OrdineRepo extends CrudRepository<Ordine, Long>{

}
