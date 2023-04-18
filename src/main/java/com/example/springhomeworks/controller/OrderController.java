package com.example.springhomeworks.controller;


import com.example.springhomeworks.service.OrderService;
import com.example.springhomeworks.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
public class OrderController {

    private final ProductService productService;
    private final OrderService orderService;

    public OrderController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }
    @GetMapping("/order")
    public String allOrders(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "allOrders.html";
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable Integer id, Model model){
        model.addAttribute("order", orderService.findById(id));

        return "order.html";
    }

    @GetMapping("/createOrder")
    public String addOrder(Model model) {
        model.addAttribute("products", productService.getAll());
        return "createOrder.html";
    }
    @PostMapping("/createOrder")
    public RedirectView createOrder(@RequestParam("selectedProducts") List<Integer> productsId){

        orderService.createOrder(productsId);

        return new RedirectView("/order");
    }
}
