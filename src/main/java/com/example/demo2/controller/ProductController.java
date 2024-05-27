package com.example.demo2.controller;

import com.example.demo2.mysql.dao.ProductDAO;
import com.example.demo2.mysqlentity.Product;
import com.example.demo2.response.CustomResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductDAO productDAO;

    @GetMapping("/get")
    public <T>ResponseEntity<T> getAll(){
        try {
            var product = productDAO.findAll();

            CustomResponse cr = new CustomResponse();
            cr.setData(product);
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
