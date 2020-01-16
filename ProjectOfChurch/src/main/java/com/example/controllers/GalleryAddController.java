package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.models.Picture;
import com.example.service.PictureService;

@Controller
public class GalleryAddController {
	
	@Autowired
	private PictureService picSer;
	
	
	
	@GetMapping(value="/addPictures")
	public String viewPicturesInGallery(Model model) {
		List <Picture> picList = picSer.getAllPictures(); 
		model.addAttribute("picList", picList);
		return"view/adminPicturesInGallery";
		}
	
	@PostMapping(value="/updateGallery")
	public String updateGallery(Model model,@RequestParam("file") List <MultipartFile> filesFromRequest,
			                                @RequestParam("removePicture") List<String> removePicture,   
			                                RedirectAttributes redirectAttributes) {
		if (!filesFromRequest.get(0).isEmpty()) {
		    picSer.uploadFilesInGallery(filesFromRequest);
		    redirectAttributes.addFlashAttribute("messageSuccessUpload", "Фотографија успешно додата...");
		 }else {
			 redirectAttributes.addFlashAttribute("blankUpload", "Овог пута нисте додавали нове фотографије...");
		 }
		   
		if (!removePicture.isEmpty()) {
		   picSer.deletePictureFromJS(removePicture);
		   redirectAttributes.addFlashAttribute("messageSuccessDelete", "Фотографија успешно обрисана...");
		}
		 
		return"redirect:/addPictures";
}
}