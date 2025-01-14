package com.cosmocats.controllers;

import com.cosmocats.domain.Product;
import com.cosmocats.dto.product.ProductApiResponse;
import com.cosmocats.dto.product.ProductCreateDTO;
import com.cosmocats.dto.product.ProductUpdateDTO;
import com.cosmocats.exceptions.ProductException;
import com.cosmocats.mapper.ProductMapper;
import com.cosmocats.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductApiResponse>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductApiResponse> responseList = products.stream()
                .map(productMapper::toProductApiResponse)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductApiResponse> getProductById(@PathVariable UUID id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductException(id));
        return ResponseEntity.ok(productMapper.toProductApiResponse(product));
    }

    @PostMapping
    public ResponseEntity<ProductApiResponse> createProduct(@RequestBody @Valid ProductCreateDTO createDTO) {
        Product createdProduct = productService.createProduct(createDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productMapper.toProductApiResponse(createdProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductApiResponse> updateProduct(
            @PathVariable UUID id,
            @RequestBody @Valid ProductUpdateDTO updateDTO
    ) {
        Product updatedProduct = productService.updateProduct(id, updateDTO);
        return ResponseEntity.ok(productMapper.toProductApiResponse(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}









