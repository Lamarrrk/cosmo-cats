package com.cosmocats.mapper;

import com.cosmocats.domain.Product;
import com.cosmocats.dto.product.ProductApiResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductApiResponse toProductApiResponse(Product product);
}











