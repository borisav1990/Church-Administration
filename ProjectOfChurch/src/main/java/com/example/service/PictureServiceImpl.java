package com.example.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.example.models.Picture;
import com.example.repository.PictureRepository;


@Service
@Transactional
public class PictureServiceImpl implements PictureService {
	
	@Autowired
	private PictureRepository picRep;
	
	@Autowired 
	private UploadPathService uploadPathServ;
	
	@Autowired
	private ServletContext context;

	@Override
	public List<Picture> getAllPictures() {
		return (List<Picture>) picRep.findAll();
		
		
		
	}


	@Override
	public List<MultipartFile> uploadFilesInGallery(List<MultipartFile> filesFromRequest) {
		if (!filesFromRequest.get(0).isEmpty()) {
			for (MultipartFile file : filesFromRequest) {
			System.out.println("--------->>>>>" + file.getOriginalFilename() );
			String fileOrgName = file.getOriginalFilename();
			String modifiedFileName =  
					FilenameUtils.getBaseName(fileOrgName)+ "," +System.currentTimeMillis()+","+FilenameUtils.getExtension(fileOrgName);
			File storeFile = uploadPathServ.getFilePath(modifiedFileName, "gallery");
			if(storeFile != null ) {
				try {
					FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
					
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
			
			Picture p = new Picture ();
			p.setFileExtension(FilenameUtils.getExtension(fileOrgName));
			p.setFileName(fileOrgName);
			p.setFileModifiedName(modifiedFileName);
			p.setUploadedDate(new Date());
			picRep.save(p);
			}
		
		}
		return filesFromRequest;
	}


	@Override
	public List<String> deletePictureFromJS(List<String> removePicture) {
		String [] arrayForDelete = new String[10];
		 String nameFromImage = "";
		 int posImgIn = 0;
		 
		 for (String nameImgFromJS : removePicture) {
			 System.out.println("------------>" + nameImgFromJS);
			 if(nameImgFromJS.equals("jpg")) {
				 nameFromImage = nameFromImage + nameImgFromJS;
				 arrayForDelete [posImgIn] = nameFromImage;
				 posImgIn ++;
				 nameFromImage = "";
				 
			 }else {
				 nameFromImage = nameFromImage + nameImgFromJS + ",";
				 
			 }
			 
			 System.out.println("------------>" + nameFromImage); 
			
		}
		
		 //checking in file and delete file
		 
		 for (String fileForDelete : arrayForDelete) {
				File dbFile = new File(context.getRealPath("/gallery/"+ fileForDelete));
				if(dbFile.exists()) {
					dbFile.delete();
					}
				}	
		
		picRep.deleteAllfromList(arrayForDelete);
	
		
		
		return removePicture;
	}

	
}
