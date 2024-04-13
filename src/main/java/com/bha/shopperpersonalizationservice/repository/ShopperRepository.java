package com.bha.shopperpersonalizationservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bha.shopperpersonalizationservice.entity.Shopper;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Long> {
	Optional<Shopper> findByShopperId(String shopperId);
}