package com.example.springhomeworks.service;

import com.example.springhomeworks.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(Product.builder().id(1).cost(100).name("Pizza").build());
        products.add(Product.builder().id(2).cost(200).name("cheeseburger").build());
        products.add(Product.builder().id(3).cost(100).name("sushi").build());
    }

    public List<Product> getAll(){
        return products;
    }

    public Product findById(int id){
        return products.stream().filter(product -> product.getId() == id).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

    }


}
