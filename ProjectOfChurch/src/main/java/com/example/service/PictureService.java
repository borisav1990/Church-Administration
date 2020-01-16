package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.models.Picture;

public interface PictureService {

	List<Picture> getAllPictures();

	List<MultipartFile> uploadFilesInGallery(List<MultipartFile> filesFromRequest);

	List<String> deletePictureFromJS(List<String> removePicture);


}
