package com.interior.shivaniInteriorproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interior.shivaniInteriorproject.model.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer>{

}
