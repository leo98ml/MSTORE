package com.piattaforme.MStore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.piattaforme.MStore.entity.TestEntity;


@Repository
public interface TestRepository extends CrudRepository<TestEntity,Long>{

	
}
