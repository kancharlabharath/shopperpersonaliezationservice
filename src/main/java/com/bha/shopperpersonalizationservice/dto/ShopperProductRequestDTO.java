package com.bha.shopperpersonalizationservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopperProductRequestDTO {

    private List<ShelfItemDTO> shelf;

    
}
