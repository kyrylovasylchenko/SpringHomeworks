package com.example.springhomeworks.service;

import com.example.springhomeworks.model.Product;
import com.example.springhomeworks.repository.OrderProductsRepository;
import com.example.springhomeworks.repository.ProductRepository;
import org.springframework.stereotype.Service;

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
        return productRepository.getAll();
    }

    public Product findById(int id){
        return productRepository.getById(id);
    }


    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(int id) {
        orderProductsRepository.deleteByProduct(id);
        productRepository.delete(id);
    }

    public void update(Product product) {
        productRepository.update(product);
    }
}
