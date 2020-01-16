package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.models.Household;
import com.example.models.Parishioner;
import com.example.models.Street;
import com.example.repository.HouseRepository;
import com.example.repository.ParishionerRepository;
import com.example.repository.StreetRepository;

@Service
@Transactional
public class HouseServImpl implements HouseholdService{
	
	@Autowired
	private StreetRepository streetRep;
	@Autowired
	private HouseRepository houseRep;
    @Autowired
    private ParishionerRepository parRep;
	
//--------------------------------------------------
	@Override
	public List<Street> getAllStreet() {
		return (List<Street>) streetRep.findAll();
	}
//--------------------------------------------------	


	@Override
	public Household save(Household household) {
		 return houseRep.save(household);
		
	}
//-------------------------------------------------
	@Override
	public Household getLatestfromHousehold() {
		 return houseRep.getLatestfromHousehold();
	}

//-------------------------------------------------
	@Override
	public Parishioner saveParishioner(Parishioner parishioner) {
		if (!parishioner.getFirstName().isEmpty()) {
			return parRep.save(parishioner);
		}
		return null;
		}
//------------------------------------------------	
    @Override
	public List<Household> getAllHousehold() {
		return (List<Household>) houseRep.findAll(); 
	}
//------------------------------------------------	
    @Override
	public List<Parishioner> getAllParishioners() {
		return (List<Parishioner>) parRep.findAll();
	}

//------------------------------------------------
	@Override
	public void deleteHouseholdAndParishionersByHouseholdId(Long householdId) {
		parRep.deleteParishionersByHouseholdId(householdId);
		houseRep.deleteById(householdId);
	   }
//-----------------------------------------------
    @Override
	public Household getById(Long householdId) {
		return houseRep.findById(householdId).get();
	}
//----------------------------------------------    
	

}
