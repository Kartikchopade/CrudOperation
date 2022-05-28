package com.example.demo.service;

import com.example.demo.model.Product;
import java.util.List;

import java.util.Optional;
public interface ProductService
{
    //List<Product> getAllActiveProducts();  
    Optional<Product> getProductById(Long id);
}
