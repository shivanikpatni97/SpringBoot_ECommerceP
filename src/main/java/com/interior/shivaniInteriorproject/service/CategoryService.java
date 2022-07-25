package com.interior.shivaniInteriorproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interior.shivaniInteriorproject.model.Category;
import com.interior.shivaniInteriorproject.repository.ICategoryRepository;

@Service
public class CategoryService {
	@Autowired
	ICategoryRepository categoryRepository;
	
	public List<Category> getcategoryAll() {
		return categoryRepository.findAll();
	}

	public void addCategory(Category category) {
		categoryRepository.save(category);	

	}
	
	public void deleteCategorybyId(int id) {
		categoryRepository.deleteById(id);
	}

	public Optional<Category> getCategoryById(int id) {
		return categoryRepository.findById(id);
		}
}
