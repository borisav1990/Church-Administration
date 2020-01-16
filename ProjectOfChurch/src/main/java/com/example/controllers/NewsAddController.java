package com.example.controllers;



import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.models.News;
import com.example.models.NewsImage;
import com.example.repository.NewsImageRepository;
import com.example.service.NewsService;





@Controller
public class NewsAddController {
	
	@Autowired
	private NewsService newSer;
	
	@Autowired
	private NewsImageRepository imgRep;
	
	@GetMapping(value="/admin")
	public String dashboard(Model model){
		
	    
		return "view/dashboard";
	}
	
	

//---------------------------------------------------------------------------------------	
	
	@GetMapping(value="/addNews")
	public String user(Model model){
		List<News> newsList = newSer.getAllNews();
		List<NewsImage> newsImageList = newSer.getAllImagesFromNews();
		model.addAttribute("newsList", newsList); // I'm sending a list of existing to the browser
		model.addAttribute("news", new News()); // Sending blank news form.
		model.addAttribute("newsImage", new ArrayList<NewsImage>()); //Sending blank field of images to browser for upload.
		model.addAttribute("newsImageList", newsImageList);
		model.addAttribute("isAdd", true); //for button name(save or update).
	    
		return "view/adminNews";
	}
	
//--------------------------------------------------------------------------------------	
	
	@PostMapping(value="/saveNews")
	public String saveNews(@ModelAttribute News news, RedirectAttributes redirectAttributes, Model model) {
		News dbNews = newSer.saveNews(news);
		model.addAttribute("", dbNews);
		return "redirect:/addNews";
		
	}
//--------------------------------------------------------------------
	
	@GetMapping(value="/deleteNews/{newsId}")
	public String deleteNews(@PathVariable Long newsId, RedirectAttributes redirectAttributes){
		newSer.deleteImageByNewsId(newsId);
		newSer.deleteNewsById(newsId);
		redirectAttributes.addFlashAttribute("successMessage", "Вест је успешно избрисана...");
		
		
		return "redirect:/addNews";
	}
	
//---------------------------------------------------------------------	
     @GetMapping(value="/editNews/{newsId}")
     public String editNews(@PathVariable Long newsId, Model model) {
    	 News news = newSer.findNewById(newsId);
    	 model.addAttribute("news", news);
    	 
    	 List <NewsImage> newsImgfromNews = imgRep.findNewsImageById(newsId);
    	 model.addAttribute("newsImg", newsImgfromNews);
    	 
    	 List<News> newsList = newSer.getAllNews();
    	 model.addAttribute("newsList", newsList);
    	 
    	 
 		 List<NewsImage> newsImageList = newSer.getAllImagesFromNews();
 		 model.addAttribute("newsImageList", newsImageList);
    	 
    	 model.addAttribute("isAdd", false);
		 return "view/adminNews";
    	 
    	 
     }

//----------------------------------------------------------------------     
     @PostMapping(value="/updateNews")
        public String updateNews(@ModelAttribute News news, RedirectAttributes redirectAttributes, Model model) {
 		 News n = newSer.updateNews(news);
 		 if(n!= null) {
 			redirectAttributes.addFlashAttribute("successMessage" , "Вест је успешно ажурирана...");
			return "redirect:/addNews";
			}else {
				redirectAttributes.addFlashAttribute("successMessage" , "Нажалост вест није ажурирана, покушајте поново...");
				return "redirect:/addNews";
				
			}
 		 }
     }
