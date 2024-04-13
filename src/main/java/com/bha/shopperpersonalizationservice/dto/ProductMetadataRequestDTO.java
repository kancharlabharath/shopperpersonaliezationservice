package com.bha.shopperpersonalizationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMetadataRequestDTO {

    private String productId;

    private String category;

    private String brand;

    
}
