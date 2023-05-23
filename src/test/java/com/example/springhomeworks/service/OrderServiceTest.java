package com.example.springhomeworks.service;

import com.example.springhomeworks.entity.Order;
import com.example.springhomeworks.entity.Product;
import com.example.springhomeworks.model.OrderDTO;
import com.example.springhomeworks.repository.OrderProductsRepository;
import com.example.springhomeworks.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceTest {

    private final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);


    private final OrderProductsRepository orderProductsRepository = Mockito.mock(OrderProductsRepository.class);

    private final ProductService productService = Mockito.mock(ProductService.class);
    private final OrderService orderService = new OrderService(orderRepository, orderProductsRepository, productService);
    private Product productMock;
    private Order orderMock;
    private Timestamp currentTime;

    @BeforeEach
    public void init() {
        currentTime = Timestamp.valueOf(LocalDateTime.now());
        productMock = Product.builder().cost(100).name("sushi").id(1).build();
        orderMock = Order.builder().date(currentTime).cost(100).id(1).build();
    }


    @Test
    public void findById(){
        List<Integer> productsId = new ArrayList<>(){{add(1);}};
        OrderDTO expected = OrderDTO.builder().date(currentTime).cost(100).products(List.of(productMock)).id(1).build();

        Mockito.when(orderRepository.findById(Mockito.any())).thenReturn(Optional.of(orderMock));
        Mockito.when(orderProductsRepository.findAllProductIdByOrderId(Mockito.anyInt())).thenReturn(productsId);
        Mockito.when(productService.findById(Mockito.anyInt())).thenReturn(productMock);


        OrderDTO actual = orderService.findById(1);

        Assertions.assertEquals(expected, actual);

    }
    @Test
    public void getAll(){

        List<Order> ordersMock = List.of(Order.builder().date(currentTime).cost(100).id(1).build());
        List<Order> expected = List.of(Order.builder().date(currentTime).cost(100).id(1).build());

        Mockito.when(orderRepository.findAll()).thenReturn(ordersMock);


        Assertions.assertEquals(expected, orderService.getAll());

    }

    @Test
    public void createOrder(){


        OrderDTO expected = OrderDTO.builder().date(currentTime).cost(orderMock.getCost()).id(1).products(List.of(productMock)).build();

        Mockito.when(productService.findById(Mockito.anyInt())).thenReturn(productMock);
        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(orderMock);


        Assertions.assertEquals(expected, orderService.createOrder(expected));
        Assertions.assertEquals(expected, orderService.createOrder(List.of(1)));
    }
    @Test
    public void update(){


        OrderDTO expected = OrderDTO.builder().date(currentTime).cost(orderMock.getCost()).id(1).products(List.of(productMock)).build();

        Mockito.when(productService.findById(Mockito.anyInt())).thenReturn(productMock);
        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(orderMock);


        Assertions.assertEquals(expected, orderService.update(expected));
    }
}
