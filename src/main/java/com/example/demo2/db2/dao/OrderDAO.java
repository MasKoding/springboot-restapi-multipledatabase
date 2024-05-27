package com.example.demo2.db2.dao;

import com.example.demo2.db2entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends JpaRepository<Order,Object> {
}
