package com.bha.shopperpersonalizationservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String productId;
	private String category;
	private String brand;

	// Constructor accepting three String parameters
	public Product(String productId, String category, String brand) {
		this.productId = productId;
		this.category = category;
		this.brand = brand;
	}
}