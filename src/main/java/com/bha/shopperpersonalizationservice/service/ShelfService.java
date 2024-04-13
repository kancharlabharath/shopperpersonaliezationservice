package com.bha.shopperpersonalizationservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bha.shopperpersonalizationservice.entity.Shelf;

@Service
public interface ShelfService {
	
	Optional<Shelf> getShelfByShopperId(String shopperId);
	
	List<Shelf> getAllShelfShopperId();

}
