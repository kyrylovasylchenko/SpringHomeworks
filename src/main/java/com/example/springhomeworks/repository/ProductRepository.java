package com.example.springhomeworks.repository;

import com.example.springhomeworks.entity.Product;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
