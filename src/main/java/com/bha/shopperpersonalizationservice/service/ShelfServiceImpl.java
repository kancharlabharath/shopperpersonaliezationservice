package com.bha.shopperpersonalizationservice.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bha.shopperpersonalizationservice.entity.Shelf;
import com.bha.shopperpersonalizationservice.repository.ShelfRepository;

@Service
@CacheConfig(cacheNames = "shelves")
public class ShelfServiceImpl implements ShelfService {

	private static final Logger logger = LoggerFactory.getLogger(ShelfServiceImpl.class);
	
	@Autowired
	private ShelfRepository shelfRepository;

	@Cacheable
	public Optional<Shelf> getShelfByShopperId(String shopperId) {
		logger.info("Fetching shelf for shopperId: {}", shopperId);
		return shelfRepository.findByShopperId(shopperId);
	}

	@Cacheable
	public List<Shelf> getAllShelfShopperId() {

		logger.info("Fetching all shelves");
		return shelfRepository.findAll();
	}
}