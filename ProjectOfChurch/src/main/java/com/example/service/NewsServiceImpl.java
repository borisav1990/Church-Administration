package com.example.service;



import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.models.News;
import com.example.models.NewsImage;
import com.example.repository.NewsImageRepository;
import com.example.repository.NewsRepository;



@Service
@Transactional
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsRepository newsRep;
	@Autowired
	private UploadPathService uploadService;
	@Autowired
	private NewsImageRepository newsImgRep;
	@Autowired 
	private ServletContext context;

//----------------------------------------------------
	
	@Override
	public List<News> getAllNews() {
		return (List<News>) newsRep.findAll();
		}
//---------------------------------------------------	
	

	@Override
	public News saveNews(News news) {
		News dbNews = newsRep.save(news);
		if (news != null && news.getImageList() != null && news.getImageList().size() > 0) {
			for (MultipartFile file : news.getImageList()) {
				if(file!=null && StringUtils.hasText(file.getOriginalFilename())) {
					String fileOrgName = file.getOriginalFilename();//read file from request
					 //creating a new file with a new name
					String modifiedFileName =  
							FilenameUtils.getBaseName(fileOrgName)+ "," +System.currentTimeMillis()+","+FilenameUtils.getExtension(fileOrgName);
					
					File storeFile = uploadService.getFilePath(modifiedFileName, "images");
					if(storeFile != null ) {
						try {
							FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
							
						} catch (Exception e) {
							e.printStackTrace();
							
						}
					}
					
					NewsImage image = new NewsImage();
					image.setFileExtension(FilenameUtils.getExtension(fileOrgName));
					image.setFileName(fileOrgName);
					image.setModifiedFileName(modifiedFileName);
					image.setNews(dbNews);
					newsImgRep.save(image);
					}	
				}
			}
		return dbNews;

	}
//--------------------------------------------------------

	@Override
	public List<NewsImage> findNewsImageById(Long newsId) {
		
		return newsImgRep.findNewsImageById(newsId);
	}

//-----------------------------------------------------------
	@Override
	public List<NewsImage> getAllImagesFromNews() {
		return (List<NewsImage>) newsImgRep.findAll();
	}

//-----------------------------------------------------------
	@Override
	public void deleteImageByNewsId(Long newsId) {
		List<NewsImage> newsImage = newsImgRep.findNewsImageById(newsId);
		if(newsImage != null && newsImage.size()>0) {
			for(NewsImage dbImg : newsImage) {
				File dbStoreFile = new File(context.getRealPath("/images/" + File.separator + dbImg.getModifiedFileName()));
				if(dbStoreFile.exists()) {
					dbStoreFile.delete();
				}
			}
			newsImgRep.deleteImagesByNewsId(newsId);
			}
		}
//------------------------------------------------------------

	@Override
	public void deleteNewsById(Long newsId) {
		newsRep.deleteById(newsId);
		
	}
//------------------------------------------------------------

	@Override
	public News findNewById(Long newsId) {
		 Optional<News> news =  newsRep.findById(newsId);
		 if (news.isPresent()) {
			  return news.get();
			  }
		 return null;
		 }
//-----------------------------------------------------------


	@Override
	public News updateNews(News news) {
		 News n = newsRep.save(news);
		 if(news != null && news.getRemoveImages() != null &&  news.getRemoveImages().size() > 0) {
			 
			 //Read array string from JavaScript array and parse...
			 String [] arrayForDelete = new String[10];
			 String nameFromImage = "";
			 int posImgIn = 0;
			 
			 for (String nameImgFromJS : news.getRemoveImages()) {
				 if(nameImgFromJS.equals("jpg")) {
					 nameFromImage = nameFromImage + nameImgFromJS;
					 arrayForDelete [posImgIn] = nameFromImage;
					 posImgIn ++;
					 nameFromImage = "";
					 
				 }else {
					 nameFromImage = nameFromImage + nameImgFromJS + ",";
					 
				 }
				
			}
			
			 //checking in file and delete file
			 
			 for (String fileForDelete : arrayForDelete) {
					File dbFile = new File(context.getRealPath("/images/"+ fileForDelete));
					if(dbFile.exists()) {
						dbFile.delete();
						}
					}
			 newsImgRep.deleteFilesByNewsIdAndImageNames(news.getId() , arrayForDelete);
			 
			 
			
		}
		 //Check and save news Image if exists and save in database and files....
		 
		 for(MultipartFile file: news.getImageList()) { 
				if(file!=null && StringUtils.hasText(file.getOriginalFilename())) {
					String fileName = file.getOriginalFilename(); //ime fajla
					String modifiedFileName = FilenameUtils.getBaseName(fileName) + ", " + System.currentTimeMillis() + ", " + FilenameUtils.getExtension(fileName); //slaze fajl za snimanje...
					File storeFile =  uploadService.getFilePath(modifiedFileName,"images"); //mesto snimanja
					if(storeFile != null ) {
						try {
							FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
							
						} catch (Exception e) {
							e.printStackTrace();
							
						}
					}
					//smestanje podataka u bazu...
					NewsImage files= new NewsImage();
					files.setFileExtension(FilenameUtils.getExtension(fileName));
					files.setFileName(fileName);
					files.setModifiedFileName(modifiedFileName);
					files.setNews(n);
					newsImgRep.save(files);
					}
				}
					
		 
		 
		 return n ;
	}






	

}
