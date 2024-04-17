package com.bha.shopperpersonalizationservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bha.shopperpersonalizationservice.entity.Product;
import com.bha.shopperpersonalizationservice.service.ProductService;
import java.util.List;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api/e-commerce")
public class ExternalApiController {

    private static final Logger logger = LoggerFactory.getLogger(ExternalApiController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/personalized-products")
    public ResponseEntity<List<Product>> getPersonalizedProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String productId,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

	
    	Pageable pageable;
        if (sortBy != null) {
            Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            pageable = PageRequest.of(page, limit, direction, sortBy);
        } else {
            // If sortBy is not provided, default to sorting by id
            Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            pageable = PageRequest.of(page, limit, direction, "id");
        }

        // Perform query
        Page<Product> productPage = productService.getPersonalizedProducts(category, brand, productId, pageable);

        // Log the request
        logger.info("Retrieving personalized products with filters: category={}, brand={}, productId={}, limit={}, page={}, sortBy={}, sortOrder={}",
                category, brand, productId, limit, page, sortBy, sortOrder);

        if (productPage.isEmpty()) {
            // Return BAD_REQUEST if no products are found
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
        return ResponseEntity.ok(productPage.getContent());
    }
}
