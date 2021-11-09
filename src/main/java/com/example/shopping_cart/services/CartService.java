package com.example.shopping_cart.services;

import com.example.shopping_cart.models.Product;
import com.example.shopping_cart.models.Cart;
import com.example.shopping_cart.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CartService {


    @Autowired
    ICartRepository cartRepository;

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    public Cart createCart(Long id,Product product){
        Cart cart = new Cart();
        cart.setId(id);
        cart.setTotal_price(cart.getTotal_price()+product.getPrice());
        cart.setTotal_quantity(cart.getTotal_quantity()+product.getQuantity());

        return cartRepository.save(cart);
    }

    public Product addProduct(Long cartId, Product product) {
        Cart cart = detailCart(cartId);

        if (cart == null) {
            return null;
        }

        cart.setProducts((List<Product>) product);
        int counter =0; // to get total number of items in the list of products in cart
        double totalPrice = 0;
        for(Product item:cart.getProducts()){
            counter++;
            totalPrice+=item.getPrice();
        }
//            update cart total quantity
        cart.setTotal_quantity(counter);
        cart.setTotal_price(totalPrice);

        cartRepository.save(cart);

        return product;
    }

    public Product removeProduct(Long cartId, Product product) {
        Cart cart = detailCart(cartId);
        if (cart == null) {
            return null;
        }
        List<Product> products;
        int counter =0; // to get total number of items in the list of products in cart
        double totalPrice = 0;
        for(Product item: cart.getProducts()){
            if(!(item.getId().equals(product.getId()))) // checking other products to remove the given one
            counter++;
            totalPrice+=item.getPrice();
        }
//            update cart total quantity
        cart.setTotal_quantity(counter);
        cart.setTotal_price(totalPrice);

        cartRepository.save(cart);

        return product;
    }

    public void deleteCart(Long id){
        cartRepository.findById(id)
                .orElseThrow( ()->new RuntimeException("Cart not found with id"+ id));
        cartRepository.deleteById(id);
    }

    public Cart updateCart(Long id, Cart cart){
        cartRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cart not found with id"+ id));

        cart.setId(id);

        return cartRepository.save(cart);

    }

    public Cart detailCart(Long id){
        return cartRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Cart with id "+id+ " not found!"));
    }

    public List<Product> retrieveProducts(Long cartId) {
        Cart cart = detailCart(cartId);
        return cart.getProducts();
    }

    public Product retrieveProduct(Long cartId, Long productId) {
        Cart cart = detailCart(cartId);

        for (Product product : cart.getProducts()) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }

        return null;
    }


}
