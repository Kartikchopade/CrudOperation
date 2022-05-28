package com.example.demo.service;


import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;  
    
    @Override
    public Optional<Product> getProductById(Long id)
    {
        return productRepository.findById(id);
    }
}
