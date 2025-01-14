package com.cosmocats.service;

import com.cosmocats.domain.Product;
import com.cosmocats.dto.product.ProductCreateDTO;
import com.cosmocats.dto.product.ProductUpdateDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(UUID productId);
    Product createProduct(ProductCreateDTO product);
    Product updateProduct(UUID id, ProductUpdateDTO updateDTO);
    boolean deleteProductById(UUID id);
}


