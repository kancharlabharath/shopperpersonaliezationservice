package com.bha.shopperpersonalizationservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bha.shopperpersonalizationservice.dto.ShopperProductRequestDTO;
import com.bha.shopperpersonalizationservice.entity.Shelf;
import com.bha.shopperpersonalizationservice.service.ProductService;
import com.bha.shopperpersonalizationservice.service.ShelfService;

@RestController
@RequestMapping("/shelves")
public class ShelfController {

	private static final Logger logger = LoggerFactory.getLogger(ShelfController.class);

	@Autowired
	private ShelfService shelfService;

	@Autowired
	private ProductService productService;

	@GetMapping("/getAll")
	public List<Shelf> getAllShelf() {
		logger.info("Fetching all shelves");
		return shelfService.getAllShelfShopperId();
	}

	@PostMapping("/addShelvesByShopperId/{shopperId}")
	public ResponseEntity<String> receiveShopperProductList(@PathVariable String shopperId,
			@RequestBody ShopperProductRequestDTO shopperProductRequest) {
		logger.info("Receiving shopper product list for shopper ID {}", shopperId);
		ResponseEntity<?> saveShopperProductList = productService.saveShopperProductList(shopperId,
				shopperProductRequest);
		if (saveShopperProductList.getStatusCode().is2xxSuccessful()) {
			logger.info("Shopper product list received and stored successfully for shopper ID {}", shopperId);
			return ResponseEntity.ok("Shopper product list received and stored successfully.");
		}

		else {
			logger.error("Failed to receive shopper product list for shopper ID {}", shopperId);
			return ResponseEntity.status(saveShopperProductList.getStatusCode()).body("please enter valid shopperid");
		}

	}

	/*
	 * @GetMapping("/{shopperId}") public ResponseEntity<Shelf>
	 * getShelfByShopperId(@PathVariable String shopperId) { return
	 * shelfService.getShelfByShopperId(shopperId).map(ResponseEntity::ok)
	 * .orElseGet(() -> ResponseEntity.notFound().build()); }
	 */

}
