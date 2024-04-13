package com.bha.shopperpersonalizationservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bha.shopperpersonalizationservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	 List<Product> findByCategoryAndBrand(String category, String brand);
	    List<Product> findByCategory(String category);
	    List<Product> findByBrand(String brand);
	    
	    Page<Product> findByCategoryAndBrand(String category, String brand, Pageable pageable);

	    Page<Product> findByCategory(String category, Pageable pageable);

	    Page<Product> findByBrand(String brand, Pageable pageable);
}
