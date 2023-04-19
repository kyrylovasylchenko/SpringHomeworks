package com.example.springhomeworks.repository.mapper;

import com.example.springhomeworks.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Order.builder().id(rs.getInt(1)).date(rs.getTimestamp(2)).cost(rs.getInt(3)).build();
    }
}
