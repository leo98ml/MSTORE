package com.piattaforme.MStore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.piattaforme.MStore.entity.Costumer;


@Repository
public interface CostumerRepo extends CrudRepository<Costumer,Long>{

}
