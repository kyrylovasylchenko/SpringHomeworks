package com.example.springhomeworks.service;

import com.example.springhomeworks.entity.Product;
import com.example.springhomeworks.repository.OrderProductsRepository;
import com.example.springhomeworks.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

public class ProductServiceTest {

    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private final OrderProductsRepository orderProductsRepository = Mockito.mock(OrderProductsRepository.class);
    private final ProductService productService = new ProductService(productRepository, orderProductsRepository);

    public static Product productExpected;

    @BeforeEach
    public void init() {
       productExpected = Product.builder().name("sushi").id(1).cost(100).build();
    }

    @Test
    public void getAll(){
        List<Product> expected = List.of(productExpected);
        Mockito.when(productRepository.findAll()).thenReturn(expected);

        List<Product> actual = productService.getAll();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findById(){

        Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(productExpected));

        Product actual = productService.findById(1);

        Assertions.assertEquals(productExpected, actual);
    }

    @Test
    public void save(){

        Mockito.when(productRepository.save(Mockito.any())).thenReturn(productExpected);

        Product actual = productService.save(productExpected);

        Assertions.assertEquals(productExpected, actual);

    }
    @Test
    public void update(){

        Mockito.when(productRepository.save(Mockito.any())).thenReturn(productExpected);

        Product actual = productService.update(productExpected);

        Assertions.assertEquals(productExpected, actual);

    }
}
