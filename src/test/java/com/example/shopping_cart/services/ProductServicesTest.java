package com.example.shopping_cart.services;

import com.example.shopping_cart.models.Product;
import com.example.shopping_cart.repository.IProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServicesTest {

    @Mock
    IProductRepository productRepository;

    @InjectMocks
    ProductService productService;


    @Test
    public void returnProducts(){
        when(productRepository.findAll()).thenReturn(Arrays.asList(
                new Product(1L, "T-SHIRT","TP-3443",12000d,1),
                new Product(2L, "T-SHIRT","TP-13443",11000d,1),
                new Product(3L, "SHIRT","S-32443",10000d,1)));
        assertEquals("TP-13443",productService.getAllProducts().get(1).getCode());
    }

    @Test
    public  void createProduct(){
        when(productRepository.save(ArgumentMatchers.any(Product.class))).thenReturn(new Product(2L, "T-SHIRT","TP-13443",11000d,1));
        assertEquals("TP-13443",productService.createProduct(3L, "SHIRT","S-32443",10000d,1).getCode());
//        assertEquals("M001",productService.createProduct("M001","Math 1","Mathematics I", 1).getCode());
    }

    @Test
    public void deleteProduct(){
        Product product = new Product(5L, "SHIRT","S-32443",10000d,1);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        productService.deleteProduct(product.getId());

        verify(productRepository).deleteById(product.getId());

    }


    @Test
    public void updateProduct(){
        Product product = new Product(6L, "SHIRT","S-32443",10000d,1);
        Product newProduct = new Product(7L, "SHIRT","S-32443",10000d,1);
        given(productRepository.findById(product.getId())).willReturn(Optional.of(product));

        productService.updateProduct(product.getId(),newProduct);
        verify(productRepository).save(newProduct);
        verify(productRepository).findById(product.getId());
    }

    @Test
    public void returnProduct(){
        Product product = new Product();
        product.setId(2L);

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        Product expected = productService.detailProduct(product.getId());

        assertThat(expected).isSameAs(product);

        verify(productRepository).findById(product.getId());
    }

}