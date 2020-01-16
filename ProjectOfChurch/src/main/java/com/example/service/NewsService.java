package com.example.service;

import java.util.List;

import com.example.models.NewsImage;
import com.example.models.News;

public interface NewsService {

	List<News> getAllNews();

	News saveNews(News news);

	List<NewsImage> findNewsImageById(Long newsId);

	List<NewsImage> getAllImagesFromNews();

	void deleteImageByNewsId(Long newsId);

	void deleteNewsById(Long newsId);

	News findNewById(Long newsId);

	News updateNews(News news);





	

}
