package com.bha.shopperpersonalizationservice.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bha.shopperpersonalizationservice.entity.Shopper;
import com.bha.shopperpersonalizationservice.repository.ShopperRepository;

@Service
public class ShopperServiceImpl implements ShopperService {
	private static final Logger logger = LoggerFactory.getLogger(ShopperServiceImpl.class);

	@Autowired
	private ShopperRepository shopperRepository;

	public Optional<Shopper> getShopperById(String shopperId) {
		logger.info("Fetching shopper by ID: {}", shopperId);
		return shopperRepository.findByShopperId(shopperId);
	}
}
