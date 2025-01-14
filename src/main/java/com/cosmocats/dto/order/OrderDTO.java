package com.cosmocats.dto.order;

import com.cosmocats.domain.Product;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Value
@Builder
@Jacksonized
public class OrderDTO {
    UUID id;
    List<Product> products;
    boolean completed;
}

