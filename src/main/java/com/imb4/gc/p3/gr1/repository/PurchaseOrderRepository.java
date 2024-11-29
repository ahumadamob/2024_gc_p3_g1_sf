package com.imb4.gc.p3.gr1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imb4.gc.p3.gr1.entity.PurchaseOrder;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>{
	List<PurchaseOrder> findByState(String state);
	
    boolean existsByState(String state);
    List<PurchaseOrder> findByTotalGreaterThan(double total);
}
