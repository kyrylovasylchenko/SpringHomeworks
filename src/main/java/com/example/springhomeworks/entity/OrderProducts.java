package com.example.springhomeworks.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("product_order")
@Builder
@Data
public class OrderProducts {
    @Id
    private int id;
    @Column("order_id")
    private int orderId;
    private int productId;

}
