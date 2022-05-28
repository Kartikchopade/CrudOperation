package com.example.demo.repository;

import com.example.demo.model.Product;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;


public interface ProductRepository extends JpaRepository<Product, Long>
{
    @Query(value="select * from product where prod_name like %:name%",nativeQuery=true)
    List<Product> findByprodName(@Param("name") String name);
    List<Product> findByprodDateBetween(@Param("startdate") Date startdate,@Param("enddate") Date enddate);

   
   
}
