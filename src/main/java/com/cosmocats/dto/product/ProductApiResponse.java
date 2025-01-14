package com.cosmocats.dto.product;

import com.cosmocats.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ProductApiResponse {

    private UUID id;
    private String name;
    private String description;
    private Integer price;
    private Integer quantity;
    private Category category;
}


