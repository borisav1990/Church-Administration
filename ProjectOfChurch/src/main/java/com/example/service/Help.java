package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Household;

public class Help {
	
	public static List<String> determineChange(Household h1,  Household h2) {
		
		List<String> listOfChanges = new ArrayList<>();
		
	    if((h1.getLastName().equals(h2.getLastName()))== false){
	    	
	    	listOfChanges.add("Презиме из " + h1.getLastName() +  " промењено у " + h2.getLastName() + "." );
	    	}
	    if((h1.getStreet().getId().equals(h2.getStreet().getId())) == false)  {
	    	
	    	listOfChanges.add(" Улица " + h1.getStreet().getStreetName() +  " промењена." );	
	    	}
		if((h1.getEntry().equals(h2.getEntry())) == false) {
			
			listOfChanges.add(" Промењен број улаз " + h1.getEntry() + " у број " + h2.getEntry() +".");
		}
		if((h1.getFloor().equals(h2.getFloor())) == false) {
			
			listOfChanges.add(" Спрат " + h1.getFloor() + " промењен у " + h2.getFloor() + ".");
		}
		if((h1.getNumberOfApartment().equals(h2.getNumberOfApartment())) == false) {
			
			listOfChanges.add(" Број стана" + h1.getNumberOfApartment() + " промењен у " + h2.getNumberOfApartment() + ".");
		}
		if((h1.getPatron().equals(h2.getPatron())) == false) {
			
			listOfChanges.add(" Крсна слава промењена");
		}
		
		if (listOfChanges.isEmpty()) {
			
			listOfChanges.add("Промена није било на домаћинству " + h1.getLastName()+"а.");
		}
		
		return listOfChanges;
		
	}

}
