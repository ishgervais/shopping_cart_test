package com.example.shopping_cart.services;

import com.example.shopping_cart.models.Product;
import com.example.shopping_cart.repository.IProductRepository;
import com.example.shopping_cart.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService {

    @Autowired
    IProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product createProduct(Long id,String name, String code, Double price, Integer quantity){
        Product product = new Product(id, name, code, price, quantity);

        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.findById(id)
                .orElseThrow( ()->new RuntimeException("Product not found with id"+ id));
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product product){
        productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found with id"+ id));

        product.setId(id);

        return productRepository.save(product);

    }

    public Product detailProduct(Long id){
        return productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product with id "+id+ " not found!"));
    }

}
