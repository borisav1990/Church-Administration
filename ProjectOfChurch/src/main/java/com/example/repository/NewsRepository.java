package com.example.repository;

import org.springframework.data.repository.CrudRepository;


import com.example.models.News;

public interface NewsRepository extends CrudRepository<News,Long> {

}
