package com.example.demo2.mysql.dao;

import com.example.demo2.mysqlentity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO  extends JpaRepository<Product,Integer> {
}
