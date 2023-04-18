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

}
