package com.bha.shopperpersonalizationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.bha.shopperpersonalizationservice.dto.ProductMetadataRequestDTO;
import com.bha.shopperpersonalizationservice.entity.Product;
import com.bha.shopperpersonalizationservice.repository.ProductRepository;
import com.bha.shopperpersonalizationservice.repository.ShelfRepository;
import com.bha.shopperpersonalizationservice.repository.ShopperRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

	@Mock
	private ProductRepository productRepository;

	@Mock
	private ShelfRepository shelfRepository;

	@Mock
	private ShopperRepository shopperRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private ProductServiceImpl productService;

	@Test
	void testGetProducts() {
		// Given
		Pageable pageable = Pageable.unpaged();
		List<Product> productList = new ArrayList<>();
		productList.add(new Product(1L, "productId1", "Category1", "Brand1"));
		productList.add(new Product(2L, "productId2", "Category2", "Brand2"));
		Page<Product> page = new PageImpl<>(productList);

		// Mocking repository method
		when(productRepository.findAll(pageable)).thenReturn(page);

		// When
		Page<Product> result = productService.getProducts(pageable);

		// Then
		verify(productRepository, times(1)).findAll(pageable);
		assertEquals(2, result.getContent().size());
	}

	@Test
	void testAddProductMetadata() {
		// Given
		List<ProductMetadataRequestDTO> requestDTOList = new ArrayList<>();
		requestDTOList.add(new ProductMetadataRequestDTO("productId1", "Category1", "Brand1"));
		requestDTOList.add(new ProductMetadataRequestDTO("productId2", "Category2", "Brand2"));

		// Mock ModelMapper behavior to return Product instances
		when(modelMapper.map(any(ProductMetadataRequestDTO.class), eq(Product.class))).thenAnswer(invocation -> {
			ProductMetadataRequestDTO dto = invocation.getArgument(0);
			return new Product(dto.getProductId(), dto.getCategory(), dto.getBrand());
		});

		// When
		productService.addProductMetadata(requestDTOList);

		// Then
		verify(productRepository, times(1)).saveAll(anyList());
	}
}
