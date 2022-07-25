package com.interior.shivaniInteriorproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interior.shivaniInteriorproject.model.Product;

@Repository
public interface IDesignRepository extends JpaRepository<Product, Long> {

	List<Product> findAllByCategory_Id(int id);
}
