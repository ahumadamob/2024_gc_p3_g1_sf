package com.imb4.gc.p3.gr1.service.jpa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb4.gc.p3.gr1.entity.PurchaseOrder;
import com.imb4.gc.p3.gr1.repository.PurchaseOrderRepository;
import com.imb4.gc.p3.gr1.service.IPurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService{
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Override
	public List<PurchaseOrder> getAll() {
		return purchaseOrderRepository.findAll();
	}

	@Override
	public PurchaseOrder getById(Long id) {
		return purchaseOrderRepository.findById(id).orElse(null);
	}

	@Override
	public PurchaseOrder save(PurchaseOrder purchaseOrder) {
		return purchaseOrderRepository.save(purchaseOrder);
	}

	@Override
	public void delete(Long id) {
		purchaseOrderRepository.deleteById(id);
	}

	@Override
	public boolean exists(Long id) {
		return id == null ? false : purchaseOrderRepository.existsById(id);
	}
}
