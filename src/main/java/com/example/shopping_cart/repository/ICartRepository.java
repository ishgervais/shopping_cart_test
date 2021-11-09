package com.example.shopping_cart.repository;

import com.example.shopping_cart.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart,Long> {
}
