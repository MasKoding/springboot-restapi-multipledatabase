package com.example.demo2;

import com.example.demo2.db2.dao.OrderDAO;
import com.example.demo2.db2entity.Order;
import com.example.demo2.mysql.dao.ProductDAO;
import com.example.demo2.mysqlentity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Demo2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	@Autowired
	ProductDAO productDAO;

	@Autowired
	OrderDAO orderDAO;

	@Override
	public void run(String... args) throws Exception {
		Product p = new Product();
		p.setProductCode("1234");
		p.setProductName("ATK");
		p.setDescription("Alat Tulis Kantor");
		productDAO.save(p);


		Order o = new Order();
		o.setProductCode(p.getProductCode());
		o.setOrderDate(new Date());
		o.setQty(100);
		orderDAO.save(o);

	}
}
