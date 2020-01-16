package com.example.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.models.Picture;

public interface PictureRepository extends CrudRepository<Picture, Long>{
	 @Modifying
     @Query("delete from Picture as f where f.fileModifiedName in ?1")
	void deleteAllfromList(String[] arrayForDelete);
	

}
