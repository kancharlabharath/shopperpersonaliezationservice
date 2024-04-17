package com.bha.shopperpersonalizationservice.controller;
import org.slf4j.Logger;
import org.springframework.data.domain.Sort;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bha.shopperpersonalizationservice.dto.ProductMetadataRequestDTO;
import com.bha.shopperpersonalizationservice.dto.ShopperProductRequestDTO;
import com.bha.shopperpersonalizationservice.entity.Product;
import com.bha.shopperpersonalizationservice.service.ProductService;
import com.bha.shopperpersonalizationservice.service.ProductServiceImpl;

@RestController
@RequestMapping("/api")
public class InternalApiController {

	private static final Logger logger = LoggerFactory.getLogger(InternalApiController.class);
	
	@Autowired
	private ProductService productService;
	
	
	/*
	 * @PostMapping("/fetchAllProductsPagenation") public
	 * ResponseEntity<List<Product>> getProducts(@RequestParam(defaultValue = "0")
	 * int page,
	 * 
	 * @RequestParam(defaultValue = "10") int size) { // Adjust page number to fetch
	 * data from the desired range
	 * logger.info("Fetching products with page {} and size {}", page, size);
	 * Pageable pageable = PageRequest.of(page - 1, size); Page<Product> productPage
	 * = productService.getProducts(pageable); return
	 * ResponseEntity.ok(productPage.getContent()); }
	 */
	
	
	
	
	@PostMapping("/fetchAllProductsPagenation")
	public ResponseEntity<List<Product>> getProducts(@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "asc") String sortOrder) {

	    // Adjust page number to fetch data from the desired range
	    logger.info("Fetching products with page {} and size {}", page, size);
	    // Page numbers are zero-based, so we subtract 1 from the given page number

	    Pageable pageable;
	    Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
	    Sort sort = Sort.by(direction, "id");
	    pageable = PageRequest.of(page - 1, size, sort);

	    // Fetch products from the service layer
	    Page<Product> productPage = productService.getProducts(pageable);

	    // Check if the requested page is out of bounds
	    if (productPage.isEmpty()) {
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    }

	    // Return the content of the fetched page
	    return ResponseEntity.ok(productPage.getContent());
	}



//	receive and store product metadata.
	@PostMapping("/addProducts")
	public ResponseEntity<String> addProductMetadata(
			@Validated @RequestBody List<ProductMetadataRequestDTO> productMetadataRequestDTO) {
		logger.info("Adding product metadata: {}", productMetadataRequestDTO);
		productService.addProductMetadata(productMetadataRequestDTO);
		return ResponseEntity.ok("Product metadata added successfully");
		//add
	}

	

   

}
