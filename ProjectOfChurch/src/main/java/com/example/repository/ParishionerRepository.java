package com.example.repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.models.Parishioner;

public interface ParishionerRepository extends CrudRepository<Parishioner, Long> {
	
	@Modifying
    @Query("delete from Parishioner as f where f.household.id = ?1")
	void deleteParishionersByHouseholdId(Long householdId);

}
