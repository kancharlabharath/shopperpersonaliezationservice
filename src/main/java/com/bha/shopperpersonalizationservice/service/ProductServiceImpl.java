package com.bha.shopperpersonalizationservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bha.shopperpersonalizationservice.dto.ProductMetadataRequestDTO;
import com.bha.shopperpersonalizationservice.dto.ShopperProductRequestDTO;
import com.bha.shopperpersonalizationservice.entity.Product;
import com.bha.shopperpersonalizationservice.entity.ProductShelf;
import com.bha.shopperpersonalizationservice.entity.Shelf;
import com.bha.shopperpersonalizationservice.entity.Shopper;
import com.bha.shopperpersonalizationservice.exceptionHandler.BadRequestException;
import com.bha.shopperpersonalizationservice.repository.ProductRepository;
import com.bha.shopperpersonalizationservice.repository.ShelfRepository;
import com.bha.shopperpersonalizationservice.repository.ShopperRepository;

@Service
@CacheConfig(cacheNames = "products")
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ShelfRepository shelfRepository;

	@Autowired
	private ShopperRepository shopperRepository;

	@Cacheable
	public Page<Product> getProducts(Pageable pageable) {
		logger.info("Fetching products from database with pagination: {}", pageable);
		return productRepository.findAll(pageable);
	}

	@CacheEvict(allEntries = true)
	@Transactional
	public void addProductMetadata(List<ProductMetadataRequestDTO> productMetadataRequestDTOList) {

		logger.info("Adding product metadata for {} products", productMetadataRequestDTOList.size());
		// Check if the list is null or empty
		if (productMetadataRequestDTOList == null || productMetadataRequestDTOList.isEmpty()) {
			throw new BadRequestException("Product metadata request list cannot be null or empty");
		}

		// Iterate over the list and add each product metadata
		for (ProductMetadataRequestDTO productMetadataRequestDTO : productMetadataRequestDTOList) {
			// If any item in the list is null or missing productId, throw
			// BadRequestException
			if (productMetadataRequestDTO == null || productMetadataRequestDTO.getProductId() == null) {
				throw new BadRequestException("Product metadata request cannot be null");
			}

			Product product = new Product();
			product.setBrand(productMetadataRequestDTO.getBrand());
			product.setCategory(productMetadataRequestDTO.getCategory());
			product.setProductId(productMetadataRequestDTO.getProductId());
			productRepository.save(product);
		}
	}

	@Transactional
	public ResponseEntity<?> saveShopperProductList(String shopperId, ShopperProductRequestDTO shopperProductRequest) {

		logger.info("Saving shopper product list for shopperId: {}", shopperId);
		// Retrieve or create a Shelf entity for the given shopperId
		Optional<Shopper> findByShopperId = shopperRepository.findByShopperId(shopperId);
		if (findByShopperId.isPresent()) {
			Shelf shelf = new Shelf();
			shelf.setShopperId(shopperId);
			Shelf save = shelfRepository.save(shelf);

			List<ProductShelf> productShelves = shopperProductRequest.getShelf().stream().map(shelfItemDTO -> {
				ProductShelf productShelf = new ProductShelf();
				productShelf.setProductId(shelfItemDTO.getProductId());
				productShelf.setRelevancyScore(shelfItemDTO.getRelevancyScore());
				return productShelf;
			}).collect(Collectors.toList());

			shelf.setShelf(productShelves);
			shelfRepository.save(shelf);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
		if (category != null && brand != null) {
			return productRepository.findByCategoryAndBrand(category, brand);
		} else if (category != null) {
			return productRepository.findByCategory(category);
		} else if (brand != null) {
			return productRepository.findByBrand(brand);
		} else {
			return productRepository.findAll();
		}
	}

	public List<Product> findByCategoryAndBrand(String category, String brand) {
		return productRepository.findByCategoryAndBrand(category, brand);
	}

	public List<Product> findByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	public List<Product> findByBrand(String brand) {
		return productRepository.findByBrand(brand);
	}

}
