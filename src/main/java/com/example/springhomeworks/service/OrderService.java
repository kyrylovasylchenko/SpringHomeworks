package com.example.springhomeworks.service;


import com.example.springhomeworks.model.Order;
import com.example.springhomeworks.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final List<Order> orders = new ArrayList<>();
    private static int currentOrderId = 1;

    private final ProductService productService;

    public OrderService(ProductService productService) {
        this.productService = productService;
    }

    public Order findById(int id){
        return orders.stream().filter(order -> order.getId() == id).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    public List<Order> getAll(){
        return orders;
    }

    public void addOrder(List<Integer> productsId){
        List<Product> products = new ArrayList<>();
        productsId.forEach(id -> products.add(productService.findById(id)));
        int cost = products.stream().mapToInt(Product::getCost).sum();
        orders.add(Order.builder().id(currentOrderId).products(products).date(LocalDateTime.now()).cost(cost).build());
        currentOrderId++;

    }
}
