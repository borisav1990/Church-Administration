package com.example.models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;
@Entity
@Table(name="pictures_of_gallery")
public class Picture implements Serializable {
	
	private static final long serialVersionUID = -440507426317837897L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="file_modified_name")
	private String fileModifiedName;
	
	@Column(name="file_extension")
	private String fileExtension;
	
	@Column(name="uploaded_date")
	private Date uploadedDate;
	
	@Transient
	private List<MultipartFile> pictureList = new ArrayList<MultipartFile>();
	
	@Transient
	private List<String> removePicture = new ArrayList<String>();

	
	
	
	
	
	
	public List<MultipartFile> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<MultipartFile> pictureList) {
		this.pictureList = pictureList;
	}

	public List<String> getRemovePicture() {
		return removePicture;
	}

	public void setRemovePicture(List<String> removePicture) {
		this.removePicture = removePicture;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileModifiedName() {
		return fileModifiedName;
	}

	public void setFileModifiedName(String fileModifiedName) {
		this.fileModifiedName = fileModifiedName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
