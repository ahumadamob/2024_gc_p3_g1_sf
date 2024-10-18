package com.imb4.gc.p3.gr1.service;

import java.util.List;

import com.imb4.gc.p3.gr1.entity.PurchaseOrder;

public interface IPurchaseOrderService {
	List<PurchaseOrder> getAll();
    PurchaseOrder getById(Long id);
    PurchaseOrder save(PurchaseOrder purchaseOrder);
    void delete(Long id);
    boolean exists(Long id);
    List<PurchaseOrder> getByState(String state);
}
