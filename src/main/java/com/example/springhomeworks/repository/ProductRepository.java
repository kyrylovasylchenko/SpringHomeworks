package com.example.springhomeworks.repository;

import com.example.springhomeworks.model.Product;
import com.example.springhomeworks.repository.mapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product getById(int id){
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = " + id, new ProductRowMapper());

        }catch (EmptyResultDataAccessException exc){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found", exc);
        }
    }

    public List<Product> getAll(){
        return jdbcTemplate.query("SELECT * FROM products", new ProductRowMapper());
    }

    public void save(Product product) {
        jdbcTemplate.update("INSERT INTO products (name, cost) VALUES (?,?)", product.getName(), product.getCost());
    }

    public void delete(int id) {
        try {
            jdbcTemplate.update("DELETE FROM products WHERE id = " + id);
        }catch (EmptyResultDataAccessException exc){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", exc);
        }
    }

    public void update(Product product) {
        try {
            jdbcTemplate.update("UPDATE products set name = ?, cost = ? where id = ?", product.getName(), product.getCost(), product.getId());
        }catch (EmptyResultDataAccessException exc){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", exc);
        }
    }
    }

