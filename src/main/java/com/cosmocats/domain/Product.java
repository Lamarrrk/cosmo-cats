package com.cosmocats.domain;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class Product {
    private UUID id;
    private String name;
    private String description;
    private Integer price;
    private Integer quantity;
    private Category category;
}


