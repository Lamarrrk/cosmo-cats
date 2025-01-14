package com.cosmocats.service.implementation;

import com.cosmocats.domain.Category;
import com.cosmocats.domain.Product;
import com.cosmocats.dto.product.ProductCreateDTO;
import com.cosmocats.dto.product.ProductUpdateDTO;
import com.cosmocats.exceptions.ProductException;
import com.cosmocats.service.CategoryService;
import com.cosmocats.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductImplementation implements ProductService {

    private final CategoryService categoryService;
    private final List<Product> productList = new ArrayList<>();

    public ProductImplementation(CategoryService categoryService) {
        this.categoryService = categoryService;
        initializeProductList();
    }

    private void initializeProductList() {
        productList.add(Product.builder()
                .id(UUID.randomUUID())
                .name("Galactic Star Crystal")
                .description("A rare star crystal found on Mars. Contains the word star.")
                .price(299)
                .quantity(10)
                .category(categoryService.findCategoryById(1))
                .build());
        productList.add(Product.builder()
                .id(UUID.randomUUID())
                .name("Zero-Gravity Galaxy Boots")
                .description("Boots designed for galaxy travel. Contains 'galaxy'.")
                .price(149)
                .quantity(5)
                .category(categoryService.findCategoryById(2))
                .build());
        productList.add(Product.builder()
                .id(UUID.randomUUID())
                .name("Lunar Comet Dust Sample")
                .description("A piece of comet dust. Contains 'comet'.")
                .price(499)
                .quantity(1)
                .category(categoryService.findCategoryById(3))
                .build());
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public Optional<Product> getProductById(UUID productId) {
        return productList.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
    }

    @Override
    public Product createProduct(ProductCreateDTO productDto) {
        Category category = categoryService.findCategoryById(productDto.getCategory().getId());

        Product product = Product.builder()
                .id(UUID.randomUUID())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .category(category)
                .build();

        productList.add(product);
        return product;
    }

    @Override
    public Product updateProduct(UUID id, ProductUpdateDTO updateDTO) {
        Optional<Product> existingOpt = productList.stream()
                .filter(prod -> prod.getId().equals(id))
                .findFirst();

        if (existingOpt.isEmpty()) {
            throw new ProductException(id);
        }
        Product product = existingOpt.get();
        product.setName(updateDTO.getName());
        product.setDescription(updateDTO.getDescription());
        product.setPrice(updateDTO.getPrice());
        product.setQuantity(updateDTO.getQuantity());

        if (updateDTO.getCategory() != null) {
            Category category = categoryService.findCategoryById(updateDTO.getCategory().getId());
            product.setCategory(category);
        }
        return product;
    }

    @Override
    public boolean deleteProductById(UUID id) {
        Optional<Product> productById = getProductById(id);
        productById.ifPresent(productList::remove);
        return productById.isPresent();
    }
}


