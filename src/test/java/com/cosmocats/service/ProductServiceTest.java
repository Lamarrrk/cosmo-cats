package com.cosmocats.service;

import com.cosmocats.domain.Category;
import com.cosmocats.domain.Product;
import com.cosmocats.dto.product.ProductCreateDTO;
import com.cosmocats.dto.product.ProductUpdateDTO;
import com.cosmocats.exceptions.ProductException;
import com.cosmocats.service.implementation.ProductImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductService productService;
    private CategoryService categoryService;
    private Category category;

    @BeforeEach
    void setUp() {
        categoryService = mock(CategoryService.class);
        productService = new ProductImplementation(categoryService);

        category = Category.builder().id(1).name("Electronics").build();
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();

        assertNotNull(products);
        assertEquals(3, products.size());
        assertEquals("Galactic Star Crystal", products.get(0).getName());
    }

    @Test
    void testGetProductById_existingProduct() {
        UUID existingProductId = productService.getAllProducts().get(0).getId();

        Optional<Product> product = productService.getProductById(existingProductId);

        assertTrue(product.isPresent());
        assertEquals("Galactic Star Crystal", product.get().getName());
    }

    @Test
    void testGetProductById_nonExistentProduct() {
        UUID nonExistentId = UUID.randomUUID();

        Optional<Product> product = productService.getProductById(nonExistentId);

        assertFalse(product.isPresent());
    }

    @Test
    void testCreateProduct() {
        ProductCreateDTO newProductDTO = ProductCreateDTO.builder()
                .name("Star Juice")
                .description("A refreshing juice from stars.")
                .price(15)
                .quantity(10)
                .category(category)
                .build();

        when(categoryService.findCategoryById(1)).thenReturn(category);

        Product createdProduct = productService.createProduct(newProductDTO);

        assertNotNull(createdProduct);
        assertNotNull(createdProduct.getId());
        assertEquals("Star Juice", createdProduct.getName());
        assertEquals("A refreshing juice from stars.", createdProduct.getDescription());
    }

    @Test
    void testUpdateProduct_existingProduct() {
        UUID existingProductId = productService.getAllProducts().get(0).getId();
        ProductUpdateDTO updatedProductDTO = ProductUpdateDTO.builder()
                .name("Updated Galactic Star Crystal")
                .description("Updated description")
                .price(350)
                .quantity(15)
                .category(category)
                .build();

        when(categoryService.findCategoryById(1)).thenReturn(category);

        Product updatedProduct = productService.updateProduct(existingProductId, updatedProductDTO);

        assertNotNull(updatedProduct);
        assertEquals("Updated Galactic Star Crystal", updatedProduct.getName());
        assertEquals("Updated description", updatedProduct.getDescription());
        assertEquals(350, updatedProduct.getPrice());
    }

    @Test
    void testUpdateProduct_nonExistentProduct() {
        UUID nonExistentId = UUID.randomUUID();

        ProductUpdateDTO updatedProductDTO = ProductUpdateDTO.builder()
                .name("Non-Existent Product")
                .description("Non-existent description")
                .price(400)
                .quantity(5)
                .category(category)
                .build();

        assertThrows(ProductException.class, () -> {
            productService.updateProduct(nonExistentId, updatedProductDTO);
        });
    }

    @Test
    void testDeleteProduct_existingProduct() {
        UUID existingProductId = productService.getAllProducts().get(0).getId();

        boolean result = productService.deleteProductById(existingProductId);

        assertTrue(result);
        assertFalse(productService.getProductById(existingProductId).isPresent());
    }

    @Test
    void testDeleteProduct_nonExistentProduct() {
        UUID nonExistentId = UUID.randomUUID();

        boolean result = productService.deleteProductById(nonExistentId);

        assertFalse(result);
    }
}
