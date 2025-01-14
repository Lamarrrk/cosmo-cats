package com.cosmocats.controllers;

import com.cosmocats.domain.Category;
import com.cosmocats.domain.Product;
import com.cosmocats.dto.product.ProductApiResponse;
import com.cosmocats.dto.product.ProductCreateDTO;
import com.cosmocats.dto.product.ProductUpdateDTO;
import com.cosmocats.mapper.ProductMapper;
import com.cosmocats.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductController productController;

    private Product mockProduct;
    private ProductApiResponse mockApiResponse;
    private ProductCreateDTO createDTO;
    private ProductUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockProduct = Product.builder()
                .id(UUID.randomUUID())
                .name("Space Milk")
                .description("High-quality star space milk")
                .price(10)
                .quantity(2)
                .category(Category.builder().id(1L).name("Galaxy Toys").build())
                .build();

        mockApiResponse = ProductApiResponse.builder()
                .id(mockProduct.getId())
                .name(mockProduct.getName())
                .description(mockProduct.getDescription())
                .price(mockProduct.getPrice())
                .quantity(mockProduct.getQuantity())
                .category(mockProduct.getCategory())
                .build();

        createDTO = ProductCreateDTO.builder()
                .name("Space Milk")
                .description("High-quality star space milk")
                .price(10)
                .quantity(2)
                .category(Category.builder().id(1L).build())
                .build();

        updateDTO = ProductUpdateDTO.builder()
                .name("Updated Space Milk")
                .description("Updated galaxy formula")
                .price(15)
                .quantity(5)
                .category(Category.builder().id(2L).build())
                .build();
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = List.of(mockProduct);
        when(productService.getAllProducts()).thenReturn(products);
        when(productMapper.toProductApiResponse(mockProduct)).thenReturn(mockApiResponse);

        ResponseEntity<List<ProductApiResponse>> response = productController.getAllProducts();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(mockApiResponse, response.getBody().get(0));

        verify(productService, times(1)).getAllProducts();
        verify(productMapper, times(1)).toProductApiResponse(mockProduct);
    }

    @Test
    void testGetProductById() {
        when(productService.getProductById(mockProduct.getId())).thenReturn(Optional.of(mockProduct));
        when(productMapper.toProductApiResponse(mockProduct)).thenReturn(mockApiResponse);

        ResponseEntity<ProductApiResponse> response = productController.getProductById(mockProduct.getId());
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockApiResponse, response.getBody());

        verify(productService, times(1)).getProductById(mockProduct.getId());
        verify(productMapper, times(1)).toProductApiResponse(mockProduct);
    }

    @Test
    void testCreateProduct() {
        when(productService.createProduct(createDTO)).thenReturn(mockProduct);
        when(productMapper.toProductApiResponse(mockProduct)).thenReturn(mockApiResponse);

        ResponseEntity<ProductApiResponse> response = productController.createProduct(createDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockApiResponse, response.getBody());

        verify(productService, times(1)).createProduct(createDTO);
        verify(productMapper, times(1)).toProductApiResponse(mockProduct);
    }

    @Test
    void testUpdateProduct() {
        when(productService.updateProduct(mockProduct.getId(), updateDTO)).thenReturn(mockProduct);
        when(productMapper.toProductApiResponse(mockProduct)).thenReturn(mockApiResponse);

        ResponseEntity<ProductApiResponse> response = productController.updateProduct(mockProduct.getId(), updateDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockApiResponse, response.getBody());

        verify(productService, times(1)).updateProduct(mockProduct.getId(), updateDTO);
        verify(productMapper, times(1)).toProductApiResponse(mockProduct);
    }

    @Test
    void testDeleteProduct() {
        when(productService.deleteProductById(mockProduct.getId())).thenReturn(true);

        ResponseEntity<Void> response = productController.deleteProduct(mockProduct.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProductById(mockProduct.getId());
    }
}

















