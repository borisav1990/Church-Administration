package com.example.service;

import java.util.List;

import com.example.models.Household;
import com.example.models.Parishioner;
import com.example.models.Street;

public interface HouseholdService {

	List<Street> getAllStreet();

	Household save(Household household);

	Household getLatestfromHousehold();

	Parishioner saveParishioner(Parishioner parishioner);

	List<Household> getAllHousehold();

	List<Parishioner> getAllParishioners();

	void deleteHouseholdAndParishionersByHouseholdId(Long householdId);

	Household getById(Long householdId);

}
