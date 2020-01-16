package com.example.models;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name="news")
public class News implements Serializable {

	private static final long serialVersionUID = -4541447824934111105L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="newTitle")
	private String newTitle;
	
	@Column(name="newText")
	private String newText;
	
	@Transient
	private List<MultipartFile> imageList = new ArrayList<MultipartFile>();
	
	@Transient
	private List <String> removeImages = new ArrayList<String>();
	
   
	
	
	public List<String> getRemoveImages() {
		return removeImages;
	}

	public void setRemoveImages(List<String> removeImages) {
		this.removeImages = removeImages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNewTitle() {
		return newTitle;
	}

	public void setNewTitle(String newTitle) {
		this.newTitle = newTitle;
	}

	public String getNewText() {
		return newText;
	}

	public void setNewText(String newText) {
		this.newText = newText;
	}

	public List<MultipartFile> getImageList() {
		return imageList;
	}

	public void setImageList(List<MultipartFile> imageList) {
		this.imageList = imageList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
