package com.example.springhomeworks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int id;
    private LocalDateTime date;
    private int cost;
    private List<Product> products;
}
