package com.cosmocats.dto.product;

import com.cosmocats.domain.Category;
import com.cosmocats.validation.CosmicWordCheck;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.*;
import lombok.Value;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@GroupSequence({ProductUpdateDTO.class, ExtendedValidation.class})
public class ProductUpdateDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 255, message = "Description cannot exceed 255 characters")
    @CosmicWordCheck(groups = ExtendedValidation.class)
    String description;

    @NotNull(message = "Price is mandatory")
    @Min(value = 1, message = "Price cannot be 0 or less")
    Integer price;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 1, message = "Quantity cannot be less than 1")
    Integer quantity;

    @NotNull(message = "Category is mandatory")
    Category category;
}

