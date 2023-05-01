package com.example.springhomeworks.model;

import com.example.springhomeworks.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int id;
    private Timestamp date;
    private double cost;
    private List<Product> products;
}
