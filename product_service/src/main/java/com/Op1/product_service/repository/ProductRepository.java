package com.Op1.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Op1.product_service.domain.Product;

public interface  ProductRepository extends JpaRepository<Product, Long>{

}
