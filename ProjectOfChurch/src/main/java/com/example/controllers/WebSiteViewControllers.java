package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.example.service.NewsService;
import com.example.models.News;
import com.example.models.NewsImage;
@Controller
public class WebSiteViewControllers {
	
	@Autowired
	private NewsService newSer;
	
	@GetMapping(value="/user/news")
	public String viewsNews(Model model){
		List<NewsImage> newsImageList = newSer.getAllImagesFromNews();
		List<News> newsList = newSer.getAllNews();
		model.addAttribute("newsList", newsList);
		model.addAttribute("newsImage", newsImageList );
		
		return "view/news";
		
	}
	
	
	
	/*@GetMapping(value="/user/news/{newsId}")
	public String views(@PathVariable Long newsId, Model model){
		List<NewsImage> newsImageList = newSer.findNewsImageById(newsId);
		List<News> newsList = newSer.getAllNews();
		model.addAttribute("newsList", newsList);
		model.addAttribute("newsImage", newsImageList );
		
		return "view/news";
		
	}*/
	
	
	

}
