package com.imb4.gc.p3.gr1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imb4.gc.p3.gr1.entity.PaymentMethod;
@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long>{
	List<PaymentMethod> findByName(String name);
}
