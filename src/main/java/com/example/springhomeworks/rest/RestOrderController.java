package com.example.springhomeworks.rest;

import com.example.springhomeworks.entity.Order;
import com.example.springhomeworks.model.OrderDTO;
import com.example.springhomeworks.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class RestOrderController {

    private final OrderService orderService;

    @Autowired
    public RestOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public OrderDTO get(@PathVariable int id){
        OrderDTO orderDTO = orderService.findById(id);
        return orderDTO;
    }

    @GetMapping
    public List<Order> getAll(){
        return orderService.getAll();
    }

    @PatchMapping("/{id}")
    public void update(@RequestBody OrderDTO orderDTO, @PathVariable int id){
        orderDTO.setId(id);
        orderService.update(orderDTO);
    }

    @PostMapping
    public void save(@RequestBody OrderDTO orderDTO){
        orderService.createOrder(orderDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        orderService.delete(id);
    }
}
