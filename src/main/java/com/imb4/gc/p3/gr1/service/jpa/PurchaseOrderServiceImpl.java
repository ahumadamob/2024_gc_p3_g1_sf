package com.imb4.gc.p3.gr1.service.jpa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb4.gc.p3.gr1.entity.PurchaseOrder;
import com.imb4.gc.p3.gr1.exceptions.ResourceNotFoundException;
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
	public PurchaseOrder getById(Long id) throws ResourceNotFoundException{
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

	@Override
	public List<PurchaseOrder> getByState(String state) {
		return purchaseOrderRepository.findByState(state);
	}

	@Override
	public boolean existsByState(String state) {
		return purchaseOrderRepository.existsByState(state);
	}

	@Override
	public void setearShippingMethod(PurchaseOrder purchaseOrder) {
        PurchaseOrder pOrder = purchaseOrder;
		if (!pOrder.getShippingMethod().equals("standard") && !pOrder.getShippingMethod().equals("express")) {
            throw new IllegalArgumentException("Método de envío inválido: debe ser 'standard' o 'express'");
        }
        pOrder.setShippingMethod(purchaseOrder.getShippingMethod());
        calculateEstimatedDeliveryDate(pOrder, pOrder.getShippingMethod());
    }
	
	private void calculateEstimatedDeliveryDate(PurchaseOrder purchaseOrder, String shippingMethod) {
        int daysToAdd = shippingMethod.equals("standard") ? 5 : 2;
        LocalDate estimatedDate = LocalDate.now().plusDays(daysToAdd);
        purchaseOrder.setEstimatedDeliveryDate(estimatedDate.format(DateTimeFormatter.ISO_DATE));
    }

	@Override
	public List<PurchaseOrder> findByTotalGreaterThan(double total) {
		return purchaseOrderRepository.findByTotalGreaterThan(total);
	}
}
