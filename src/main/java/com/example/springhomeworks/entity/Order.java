package com.example.springhomeworks.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
@Table("orders")
@Data
@Builder
public class Order {
    @Id
    private int id;
    private Timestamp date;
    private double cost;
}
