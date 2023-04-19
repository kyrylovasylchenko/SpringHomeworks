package com.example.springhomeworks.rest;

import com.example.springhomeworks.model.Product;
import com.example.springhomeworks.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class RestProductController {

    private final ProductService productService;
    @Autowired
    public RestProductController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping
    public List<Product> get(){
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable int id){
        return productService.findById(id);
    }

    @PostMapping
    public void create(@RequestBody Product product){
        productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        productService.delete(id);
    }

    @PatchMapping("/{id}")
    public void update(@RequestBody Product product, @PathVariable int id){
        product.setId(id);
        productService.update(product);
    }
}
