package com.Op1.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Op1.order_service.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
