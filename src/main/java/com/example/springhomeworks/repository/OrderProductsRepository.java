package com.example.springhomeworks.repository;

import com.example.springhomeworks.entity.OrderProducts;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderProductsRepository extends CrudRepository<OrderProducts, Integer> {

    @Query("SELECT product_id FROM product_order WHERE order_id = :id")
    List<Integer> findAllProductIdByOrderId(@Param("id") int id);

    @Modifying
    @Query("DELETE FROM product_order WHERE order_id = :id")
    void deleteAllByOrderId(@Param("id") int id);

    @Modifying
    @Query("DELETE FROM product_order WHERE product_id = :id")
    void deleteAllByProductId(@Param("id") int id);
}
