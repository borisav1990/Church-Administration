package com.example.service;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UploadPathServiceImpl implements UploadPathService {
	
	@Autowired ServletContext context; 

	@Override
	public File getFilePath(String modifiedFileName, String path) {
		boolean exists = new File( context.getRealPath("/" + path + "/")).exists(); 
		if(!exists) {
			new File( context.getRealPath("/" + path + "/")).mkdir();
		}  /*gore proverava da li postoji folder "images", ako ga nema napravice novi* obrati paznju da ne poziva nigde modifiedFileName*/
		
		String modifiedFilePath = context.getRealPath("/" + path + "/" + File.separator + modifiedFileName);
		File file = new File(modifiedFilePath);
		return file;
	}
}
