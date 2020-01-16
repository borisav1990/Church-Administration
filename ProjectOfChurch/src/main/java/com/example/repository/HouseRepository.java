package com.example.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.models.Household;

public interface HouseRepository extends CrudRepository<Household, Long> {
	//@Query("select f  from Household as f where f.id= (24) ")
	@Query("SELECT f FROM Household AS f WHERE f.createdDate=(SELECT MAX(createdDate) FROM Household)")
	Household getLatestfromHousehold();

    
	

}
