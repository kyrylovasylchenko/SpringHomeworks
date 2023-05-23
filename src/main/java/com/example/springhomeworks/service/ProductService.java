package com.example.springhomeworks.service;

import com.example.springhomeworks.entity.Product;
import com.example.springhomeworks.repository.OrderProductsRepository;
import com.example.springhomeworks.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderProductsRepository orderProductsRepository;

    public ProductService(ProductRepository productRepository, OrderProductsRepository orderProductsRepository) {
        this.productRepository = productRepository;
        this.orderProductsRepository = orderProductsRepository;
    }

    public List<Product> getAll(){
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    public Product findById(int id){
        return productRepository.findById(id).orElseThrow();
    }


    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(int id) {
        orderProductsRepository.deleteAllByProductId(id);
        productRepository.deleteById(id);
    }

    public Product update(Product product) {
        orderProductsRepository.deleteAllByProductId(product.getId());
       return productRepository.save(product);
    }
}
