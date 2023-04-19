package com.example.springhomeworks.repository;

import com.example.springhomeworks.model.Order;
import com.example.springhomeworks.model.Product;
import com.example.springhomeworks.repository.mapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public class OrderProductsRepository {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public OrderProductsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getProductsByOrderId(int id){
        try{
            return jdbcTemplate.query("SELECT products.id, products.name, products.cost FROM products " +
                    "JOIN product_order on product_order.product_id = products.id " +
                    "WHERE product_order.order_id = " + id, new ProductRowMapper());
        }catch (EmptyResultDataAccessException exc){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Products for order not found", exc);
        }
    }

    public void saveOrderAndProduct(int orderId, int productId){
        jdbcTemplate.update("INSERT INTO product_order (order_id, product_id) VALUES (?,?)",
                orderId, productId);
    }

    public void deleteByOrder(int orderId){
        try {
            jdbcTemplate.update("DELETE FROM product_order WHERE order_id = " + orderId);
        }catch (EmptyResultDataAccessException exc){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", exc);
        }
    }

    public void deleteByProduct(int productId) {
        try {
            jdbcTemplate.update("DELETE FROM product_order WHERE order_id = " + productId);
        }catch (EmptyResultDataAccessException exc){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", exc);
        }
    }
}
