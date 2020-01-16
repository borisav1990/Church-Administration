package com.example.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.models.Household;
import com.example.models.Parishioner;
import com.example.models.Street;
import com.example.service.Help;
import com.example.service.HouseholdService;

@Controller

public class HauseholdController {
	
	@Autowired
	private HouseholdService houseSer;
	
//--------------------------------------------------------	
	@GetMapping(value="/addHousehold")
	public String addHousehold(Model model) {
		model.addAttribute("household", new Household());
		List<Street> sl = houseSer.getAllStreet(); 
		model.addAttribute("listOfStreet" , sl);
		return "view/addHousehold";
		}
//--------------------------------------------------------
	@PostMapping(value="/saveHousehold")
	public String saveAndUpdateHousehold(Model model, @ModelAttribute Household household, RedirectAttributes redirectAttr ) {
	     if(!household.getLastName().isEmpty()) {
	    	       /**Add new**/
	    	 if (household.getId()== null) {
	    		 household.setCreatedDate(new Date());
		    	 Household hh = houseSer.save(household);
		    	 redirectAttr.addFlashAttribute("messageSuccessSave", "Породици " + hh.getLastName() + " сада додајте укућане.");
		    	 return "redirect:/addParishioner"; 
		    	  /**Update**/
			}else {
				Household h1 = houseSer.getById(household.getId());
				Household h2 = household;
				List<String> listOfChanges = Help.determineChange(h1, h2);
				Household h3 = houseSer.save(household);
                redirectAttr.addFlashAttribute("messageSuccessSave", "Породицa " + h3.getLastName() + " успешно ажурирана.");
                redirectAttr.addFlashAttribute("listOfChanges", listOfChanges);
		    	return "redirect:/viewHousehold"; 
				}
       
	     }else {
	    	 redirectAttr.addFlashAttribute("blankData", "Унос податак је био безуспешан! Поља су остала празна или недовољно попуњена.");
	    	 
	     }
	     return "redirect:/addHousehold"; 
	     
	}
//--------------------------------------------------------	
	@GetMapping(value="/addParishioner")
	public String addParishioner(Model model) {
		Household latestHousehold = houseSer.getLatestfromHousehold(); 
		model.addAttribute("latestHousehold" , latestHousehold);
		model.addAttribute("parishioner", new Parishioner());
		
		return "view/addParishioner";
		
	}
//--------------------------------------------------------
	@PostMapping(value="/saveParishioner")
	public String saveParishioner(Model model, @ModelAttribute Parishioner parishioner, RedirectAttributes redirectAttr) {
		Parishioner p = houseSer.saveParishioner(parishioner);
		if (p!=null) {
			redirectAttr.addFlashAttribute("messageSuccessSave", p.getFirstName() + " је успешно додат. Наставите са додавањем укућана или завршите унос.");
			return "redirect:/addParishioner";
			}
		return "redirect:/addParishioner";
	}
//--------------------------------------------------------	
	
	@GetMapping(value="/viewHousehold")
	public String viewHousehold(Model model) {
		List<Street> ls =  houseSer.getAllStreet();
		model.addAttribute("listOfStreet", ls );
		
		List<Household> hl = houseSer.getAllHousehold();
		model.addAttribute("listOfHouseholds" , hl);
		
		List<Parishioner> pl = houseSer.getAllParishioners(); 
		model.addAttribute("listOfParishioners" , pl);
		return "view/viewHousehold";
	}
//----------------------------------------------------------	
   @GetMapping(value="/deleteHousehold/{householdId}")
   public String deleteHouseholdAndParishioner( @PathVariable Long householdId, RedirectAttributes redirectAttr) {
	  
	   houseSer.deleteHouseholdAndParishionersByHouseholdId(householdId); 
	  // redirectAttr.addFlashAttribute("successMessage", "Вест је успешно избрисана...");
	   return "view/viewHousehold";
   }
//----------------------------------------------------------
   @GetMapping(value="/editHousehold/{householdId}")
   public String editHousehold(Model model,  @PathVariable Long householdId){
	   Household hh = houseSer.getById(householdId);
	   model.addAttribute("objOfParishioner", new Parishioner());
	   model.addAttribute("objOfHousehold", hh);
	   List<Street> ls =  houseSer.getAllStreet();
	   model.addAttribute("listOfStreet", ls );
	   return "view/editHousehold";
   }
//------------------------------------------------------------


}
