package com.interior.shivaniInteriorproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interior.shivaniInteriorproject.model.Product;
import com.interior.shivaniInteriorproject.repository.IDesignRepository;

@Service
public class DesignService {

	@Autowired
	IDesignRepository designRepository;

	public List<Product> getAllDesigns() {
		return designRepository.findAll();
	}
	
	public void addProduct(Product product) {
		designRepository.save(product);
	}
	
	public void removeProductByID(long id) {
		designRepository.deleteById(id);
	}
	
	public Optional<Product> findDesignById(long id){
		return designRepository.findById(id);
	}
	
	public List<Product> getAllDesignsByCategory(int id){
		return designRepository.findAllByCategory_Id(id);
	}
	
}
