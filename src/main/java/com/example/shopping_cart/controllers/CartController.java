package com.example.shopping_cart.controllers;


import com.example.shopping_cart.models.Cart;
import com.example.shopping_cart.models.Product;
import com.example.shopping_cart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/get-all")
    public List<Cart> getAll(){
        return cartService.getAllCarts();
    }

    @PostMapping("/add-cart")
    public Cart addCart(@RequestBody Cart cart, @RequestBody Product product){
        return cartService.createCart(cart.getId(),product);
    }

    @GetMapping("/{cartId}/products")
    public List<Product> retrieveItemsInCart(@PathVariable Long cartId) {
        return cartService.retrieveProducts(cartId);
    }

    @GetMapping("/{cartId}/products/{productId}")
    public Product retrieveDetailsForCart(@PathVariable Long cartId,
                                          @PathVariable Long productId) {
        return cartService.retrieveProduct(cartId, productId);
    }

    @PostMapping("/carts/{cartId}/products")
    public Product addItemInCart(
            @PathVariable Long cartId, @RequestBody Product newProduct) {

        return cartService.addProduct(cartId, newProduct);
    }


}
