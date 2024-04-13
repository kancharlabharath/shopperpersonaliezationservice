package com.bha.shopperpersonalizationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bha.shopperpersonalizationservice.entity.Shopper;
import com.bha.shopperpersonalizationservice.service.ShopperServiceImpl;

@RestController
@RequestMapping("/shoppers")
public class ShopperController {
	 @Autowired
	    private ShopperServiceImpl shopperService;

	    @GetMapping("/{shopperId}")
	    public ResponseEntity<Shopper> getShopperById(@PathVariable String shopperId) {
	        return shopperService.getShopperById(shopperId)
	                .map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	    
	    
}