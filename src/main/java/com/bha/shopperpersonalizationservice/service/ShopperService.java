package com.bha.shopperpersonalizationservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bha.shopperpersonalizationservice.entity.Shopper;

@Service
public interface ShopperService {
	
	Optional<Shopper> getShopperById(String shopperId);

}
