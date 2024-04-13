package com.bha.shopperpersonalizationservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bha.shopperpersonalizationservice.entity.Shelf;


@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long> {
	
	Optional<Shelf> findByShopperId(String shopperId);
}
