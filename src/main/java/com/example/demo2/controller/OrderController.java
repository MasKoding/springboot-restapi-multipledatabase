package com.example.demo2.controller;

import com.example.demo2.db2.dao.OrderDAO;
import com.example.demo2.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderDAO orderDAO;

    @RequestMapping("get")
    public <T>ResponseEntity<T> getAll(){
        try {
            var order = orderDAO.findAll();
            CustomResponse cr= new CustomResponse();
            cr.setData(order);
            cr.setMessage("success");
            cr.setStatus(HttpStatus.OK.value());
            return new ResponseEntity<T>((T) cr,HttpStatus.OK);
        }catch (Exception ex){
            CustomResponse cr = new CustomResponse();
            cr.setData(null);
            cr.setMessage("error:"+ex.getMessage());
            cr.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<T>((T) cr,HttpStatus.BAD_REQUEST);
        }


    }
}
