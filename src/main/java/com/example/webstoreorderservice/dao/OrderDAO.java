package com.example.webstoreorderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webstoreorderservice.entity.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer>{

}
