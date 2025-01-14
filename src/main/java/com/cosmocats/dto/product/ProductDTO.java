package com.cosmocats.dto.product;

import com.cosmocats.validation.CosmicWordCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ProductDTO {

    @Size(max = 100, message = "Name cannot exceed 100 characters")
    @NotBlank(message = "Product name cannot be null")
    String productName;

    String categoryId;

    @CosmicWordCheck
    @Size(max = 255, message = "Description cannot exceed 255 characters")
    String productDescription;

    @PositiveOrZero(message = "Price must be positive")
    Double price;

    @PositiveOrZero(message = "Quantity must be positive")
    int quantity;
}

