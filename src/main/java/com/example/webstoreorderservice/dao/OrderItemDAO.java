package com.example.webstoreorderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webstoreorderservice.entity.OrderItem;

@Repository
public interface OrderItemDAO extends JpaRepository<OrderItem, Integer>{

}
