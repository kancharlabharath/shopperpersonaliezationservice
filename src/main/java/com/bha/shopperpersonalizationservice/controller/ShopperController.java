package com.bha.shopperpersonalizationservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bha.shopperpersonalizationservice.entity.Shopper;
import com.bha.shopperpersonalizationservice.service.ShopperService;

@RestController
@RequestMapping("/shoppers")
public class ShopperController {

    @Autowired
    private ShopperService shopperService;

    @GetMapping("/{shopperId}")
    public ResponseEntity<Shopper> getShopperById(@PathVariable String shopperId) {
        Optional<Shopper> shopperOptional = shopperService.getShopperById(shopperId);
        if (shopperOptional.isPresent()) {
            return ResponseEntity.ok(shopperOptional.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
