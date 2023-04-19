package com.example.springhomeworks.repository.mapper;



import com.example.springhomeworks.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Product.builder()
                .id(rs.getInt(1))
                .name(rs.getString(2))
                .cost(rs.getInt(3))
                .build();
    }
}
