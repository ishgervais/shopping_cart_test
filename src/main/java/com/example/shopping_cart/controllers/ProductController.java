package com.example.shopping_cart.controllers;

import com.example.shopping_cart.models.Product;
import com.example.shopping_cart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public List<Product> getAll(){
        return productService.getAllProducts();
    }

    @PostMapping("/add-product")
    public Product addProduct(@RequestBody Product product){
        return productService.createProduct( product.getId(),product.getCode(), product.getName(), product.getPrice(), product.getQuantity());
    }

    @PostMapping("/edit-product/{productId}")
    public Product editProduct(@RequestBody Product product, @PathVariable Long productId){
        return productService.updateProduct(productId, product);
    }
}
