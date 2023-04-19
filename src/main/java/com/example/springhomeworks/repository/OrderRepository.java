package com.example.springhomeworks.repository;

import com.example.springhomeworks.model.Order;
import com.example.springhomeworks.repository.mapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    private final OrderProductsRepository orderProductsRepository;

    @Autowired
    public OrderRepository(JdbcTemplate jdbcTemplate, OrderProductsRepository orderProductsRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.orderProductsRepository = orderProductsRepository;
    }

    public Order getById(int id){
        try{
            Order order = jdbcTemplate.queryForObject("SELECT * FROM orders WHERE id = " + id, new OrderRowMapper());
            order.setProducts(orderProductsRepository.getProductsByOrderId(id));
            return order;

        }catch (EmptyResultDataAccessException exc){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", exc);
        }
    }

    public List<Order> getAll(){
        return jdbcTemplate.query("SELECT * FROM orders", new OrderRowMapper());
    }

    public int getLastOrderId(){
            return jdbcTemplate.queryForObject("SELECT id from orders " +
                    "ORDER BY id DESC " +
                    "limit 1", Integer.class);
    }

    public void save(Order order) {
        jdbcTemplate.update("INSERT INTO orders (date, cost) VALUES (?,?)", order.getDate(), order.getCost());

    }

    public void delete(int id) {
        try {
            jdbcTemplate.update("DELETE FROM orders WHERE id = " + id);
        }catch (EmptyResultDataAccessException exc){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", exc);
        }
    }

    public void update(Order order) {
        try {
            jdbcTemplate.update("UPDATE orders set date = ?, cost = ? where id = ?", order.getDate(), order.getCost(), order.getId());
        }catch (EmptyResultDataAccessException exc){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", exc);
        }
    }
}
