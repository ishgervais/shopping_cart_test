package com.example.shopping_cart.services;


import com.example.shopping_cart.models.Product;
import com.example.shopping_cart.models.Cart;
import com.example.shopping_cart.repository.ICartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartServicesTest {

    @Mock
    ICartRepository cartRepository;

    @InjectMocks
    CartService cartService;

    @Test
    public void returnCart(){
        Cart cart = new Cart();
        cart.setId(2L);

        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));

        Cart expected = cartService.detailCart(cart.getId());

        assertThat(expected).isSameAs(cart);

        verify(cartRepository).findById(cart.getId());
    }

    @Test
    public void addProductToCart(){
        Product product = new Product(1L, "SHIRT","S-32443",10000d,1);
        Cart cart = new Cart(1L);
        cart.setProducts(new ArrayList<Product>());

        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        cartService.addProduct(cart.getId(), product);


        verify(cartRepository).save(cart);
        verify(cartRepository).findById(cart.getId());

        List<Product> expected = cartService.detailCart(cart.getId()).getProducts();
        assertThat(expected).isSameAs(cart.getProducts());
        verify(cartRepository).save(cart);
    }

    @Test
    public void removeProduct(){
        Product product = new Product(1L, "SHIRT","S-32443",10000d,1);
        Cart cart = new Cart(1L);
        cart.setProducts(new ArrayList<Product>());

        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        cartService.addProduct(cart.getId(), product);


        verify(cartRepository).save(cart);
        verify(cartRepository).findById(cart.getId());

        List<Product> expected = cartService.detailCart(cart.getId()).getProducts();
        assertThat(expected).isSameAs(cart.getProducts());
        verify(cartRepository).save(cart);
    }



    @Test
    public void retrieveCartProduct() {
        Cart cart = new Cart(1L);
        cart.setProducts(new ArrayList<Product>(Arrays.asList(
                new Product(1L, "T-SHIRT","TP-3443",12000d,1),
                new Product(2L, "T-SHIRT","TP-13443",11000d,1),
                new Product(3L, "SHIRT","S-32443",10000d,1))));

        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        cartService.retrieveProducts(cart.getId());

        assertEquals("TP-13443",cartService.retrieveProducts(cart.getId()).get(1).getCode());
    }

    @Test
    public void returnCarts(){
        when(cartRepository.findAll()).thenReturn(Arrays.asList(
                new Cart(1L),
                new Cart(2L),
                new Cart(3L)));
        assertEquals("3",cartService.getAllCarts().get(2).getId());
    }

    @Test
    public  void createCart(){
        when(cartRepository.save(ArgumentMatchers.any(Cart.class))).thenReturn(new Cart(1L));
        assertEquals("1",cartService.createCart(1L, new Product(1L, "T-SHIRT","TP-3443",12000d,1)).getId());
    }
    @Test
    public void deleteCart(){
        Cart cart = new Cart(5L);
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));

        cartService.deleteCart(cart.getId());

        verify(cartRepository).deleteById(cart.getId());

    }

    @Test
    public void updateCart(){
        Cart cart = new Cart(6L);
        Cart newCart = new Cart(1L);
        given(cartRepository.findById(cart.getId())).willReturn(Optional.of(cart));

        cartService.updateCart(cart.getId(),newCart);
        verify(cartRepository).save(newCart);
        verify(cartRepository).findById(cart.getId());
    }


    @Test
    public void retrieveDetailsForProduct() {
        Product product = new Product(1L, "T-SHIRT","TP-3443",12000d,1);
        Cart cart = new Cart(2L);


        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        when(cartService.retrieveProduct(cart.getId(), product.getId())).thenReturn(product);

        String expected = "[id=1, code=T-3443, name=T-SHIRT, price=12000, quantity=1]";

        assertEquals(expected, cart.getProducts());
        verify(cartRepository).findById(cart.getId());
    }

}
