package com.example.springhomeworks.conterter;

import com.example.springhomeworks.entity.Order;
import com.example.springhomeworks.model.OrderDTO;

public class OrderConverter {

    public static OrderDTO orderToOrderDTO(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .date(order.getDate())
                .cost(order.getCost())
                .build();
    }
}
