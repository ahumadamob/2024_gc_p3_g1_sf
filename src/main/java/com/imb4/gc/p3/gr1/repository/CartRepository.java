package com.imb4.gc.p3.gr1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb4.gc.p3.gr1.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}