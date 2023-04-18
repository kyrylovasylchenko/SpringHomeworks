package com.example.springhomeworks.rest;

import com.example.springhomeworks.model.Order;
import com.example.springhomeworks.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class RestOrderController {

    private final OrderService orderService;

    @Autowired
    public RestOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable int id){
        return orderService.findById(id);
    }

    @GetMapping
    public List<Order> getAll(){
        return orderService.getAll();
    }

    @PatchMapping("/{id}")
    public void update(@RequestBody Order order, @PathVariable int id){
        order.setId(id);
        orderService.update(order);
    }

    @PostMapping
    public void save(@RequestBody Order order){
        orderService.createOrder(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        orderService.delete(id);
    }
}
