package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.models.Street;

public interface StreetRepository extends CrudRepository<Street, Long> {

}
