package com.example.shopping_cart.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Cart {
 @Id
    private Long id;
    private List<Product> products;
    private Integer total_quantity;
    private Double total_price;

    public Cart() {
    }

    public Cart(Long id) {
        this.id = id;
    }

    public Cart(Long id, Integer total_quantity, Double total_price) {
        this.id = id;
        this.total_quantity = total_quantity;
        this.total_price = total_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(Integer total_quantity) {
        this.total_quantity = total_quantity;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }
}
