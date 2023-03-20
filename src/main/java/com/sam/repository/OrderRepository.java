package com.sam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
