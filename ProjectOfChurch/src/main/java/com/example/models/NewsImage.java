package com.example.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="newsImage")
public class NewsImage implements Serializable {

	private static final long serialVersionUID = -683922182410482240L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="modified_file_name")
	private String modifiedFileName;
	
	@Column(name="file_extension")
	private String fileExtension;
	
	
	@ManyToOne
	@JoinColumn(name="news_id")
	private News news;


	
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


	public String getModifiedFileName() {
		return modifiedFileName;
	}


	public void setModifiedFileName(String modifiedFileName) {
		this.modifiedFileName = modifiedFileName;
	}


	public String getFileExtension() {
		return fileExtension;
	}


	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}


	public News getNews() {
		return news;
	}


	public void setNews(News news) {
		this.news = news;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
