package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.models.NewsImage;

@Repository
public interface NewsImageRepository extends CrudRepository<NewsImage, Long> {
	
	@Query("select f from NewsImage as f where f.news.id = ?1")
	List<NewsImage> findNewsImageById(Long newsId);
	 
	 @Modifying
     @Query("delete from NewsImage as f where f.news.id = ?1")
	 void deleteImagesByNewsId(Long userId);
	 
	 @Modifying
     @Query("delete from NewsImage as f where f.news.id = ?1 and f.modifiedFileName in (?2)")
	void deleteFilesByNewsIdAndImageNames(Long id, String[] arrayForDelete);
	

	
}


