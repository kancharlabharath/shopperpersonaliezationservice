package com.bha.shopperpersonalizationservice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bha.shopperpersonalizationservice.dto.ProductMetadataRequestDTO;
import com.bha.shopperpersonalizationservice.dto.ShopperProductRequestDTO;
import com.bha.shopperpersonalizationservice.entity.Product;

@Service
public interface ProductService {

	public Page<Product> getProducts(Pageable pageable);

	void addProductMetadata(List<ProductMetadataRequestDTO> productMetadataRequestDTO);

	
	  List<Product> getProductsByCategoryAndBrand(String category, String brand);
	  
	  List<Product> findByCategory(String category);
	  
	  List<Product> findByBrand(String brand);
	  
	  ResponseEntity<?> saveShopperProductList(String shpperId,
	  ShopperProductRequestDTO shopperProductRequest);
	 

}
