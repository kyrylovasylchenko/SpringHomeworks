package com.example.springhomeworks.service;


import com.example.springhomeworks.model.Order;
import com.example.springhomeworks.model.Product;
import com.example.springhomeworks.repository.OrderProductsRepository;
import com.example.springhomeworks.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
   private final OrderRepository orderRepository;
   private final OrderProductsRepository orderProductsRepository;

    private final ProductService productService;
    @Autowired
    public OrderService(OrderRepository orderRepository, OrderProductsRepository orderProductsRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderProductsRepository = orderProductsRepository;
        this.productService = productService;
    }

    public Order findById(int id){
        return orderRepository.getById(id);
    }

    public List<Order> getAll(){
        return orderRepository.getAll();
    }

    public void createOrder(List<Integer> productsId){
        List<Product> products = new ArrayList<>();
        productsId.forEach(id -> products.add(productService.findById(id)));
        int orderCost = products.stream().mapToInt(Product::getCost).sum();

        Order order = Order.builder().date(Timestamp.valueOf(LocalDateTime.now())).cost(orderCost).products(products).build();
        orderRepository.save(order);

        int lastOrderId = orderRepository.getLastOrderId();
        productsId.forEach(id -> orderProductsRepository.saveOrderAndProduct(lastOrderId, id));

    }

    public void createOrder(Order order){
        createOrder(order.getProducts().stream().map(Product::getId).toList());
    }

    public void delete(int id) {
        orderProductsRepository.deleteByOrder(id);
        orderRepository.delete(id);
    }

    public void update(Order order) {

        orderProductsRepository.deleteByOrder(order.getId());

        List<Integer> productIds = order.getProducts().stream().map(Product::getId).toList();
        productIds.forEach(id -> orderProductsRepository.saveOrderAndProduct(order.getId(), id));

        Timestamp orderUpdateTime = Timestamp.valueOf(LocalDateTime.now());
        int cost = order.getProducts().stream().mapToInt(Product::getCost).sum();
        Order updatedOrder = Order.builder().id(order.getId()).cost(cost).date(orderUpdateTime).build();

        orderRepository.update(updatedOrder);

    }
}
