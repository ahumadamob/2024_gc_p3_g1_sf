package com.imb4.gc.p3.gr1.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb4.gc.p3.gr1.entity.PaymentMethod;
import com.imb4.gc.p3.gr1.repository.PaymentMethodRepository;
import com.imb4.gc.p3.gr1.service.IPaymentMethodService;

@Service
public class PaymentMethodServiceImpl implements IPaymentMethodService{
	
	@Autowired
	private PaymentMethodRepository repo;
	
	@Override
	public List<PaymentMethod> getAll() {
		return repo.findAll();
	}

	@Override
	public PaymentMethod getById(Long id) {
		return repo.findById(id).orElse(null);
	}
	
	@Override
	public List<PaymentMethod> findByName(String name) {
	    return repo.findByName(name);
	}


	@Override
	public PaymentMethod save(PaymentMethod paymentMethod) {
		return repo.save(paymentMethod);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public boolean exists(Long id) {
		return id == null ? false : repo.existsById(id);
	}

}
