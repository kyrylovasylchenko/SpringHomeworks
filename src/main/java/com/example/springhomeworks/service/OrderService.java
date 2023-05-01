package com.example.springhomeworks.service;


import com.example.springhomeworks.conterter.OrderConverter;
import com.example.springhomeworks.entity.Order;
import com.example.springhomeworks.entity.OrderProducts;
import com.example.springhomeworks.entity.Product;
import com.example.springhomeworks.model.OrderDTO;
import com.example.springhomeworks.repository.OrderProductsRepository;
import com.example.springhomeworks.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public OrderDTO findById(int orderId){
        Order order = orderRepository.findById(orderId).orElseThrow();
        OrderDTO orderDTO = OrderConverter.orderToOrderDTO(order);
        List<Integer> productsId = orderProductsRepository.findAllProductIdByOrderId(orderId);
        List<Product> products = new ArrayList<>();
        productsId.forEach(id -> products.add(productService.findById(id)));
        orderDTO.setProducts(products);
        return orderDTO;
    }

    public List<Order> getAll(){
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    public void createOrder(List<Integer> productsId){
        List<Product> products = new ArrayList<>();
        productsId.forEach(id -> products.add(productService.findById(id)));
        double orderCost = products.stream().mapToDouble(Product::getCost).sum();

        Order order = Order.builder().date(Timestamp.valueOf(LocalDateTime.now())).cost(orderCost).build();
        Order savedOrder = orderRepository.save(order);

        productsId.forEach(id -> orderProductsRepository.save(OrderProducts.builder().orderId(savedOrder.getId()).productId(id).build()));

    }

    public void createOrder(OrderDTO orderDTO){
        createOrder(orderDTO.getProducts().stream().map(Product::getId).toList());
    }

    public void delete(int id) {
        orderProductsRepository.deleteAllByOrderId(id);
        orderRepository.deleteById(id);
    }

    public void update(OrderDTO orderDTO) {

        orderProductsRepository.deleteAllByOrderId(orderDTO.getId());

        orderRepository.deleteById(orderDTO.getId());
        this.createOrder(orderDTO);


    }
}
