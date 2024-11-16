package com.imb4.gc.p3.gr1.service.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb4.gc.p3.gr1.entity.PaymentMethod;
import com.imb4.gc.p3.gr1.repository.PaymentMethodRepository;
import com.imb4.gc.p3.gr1.service.IPaymentMethodService;
import com.imb4.gc.p3.gr1.exceptions.ResourceNotFoundException;

@Service
public class PaymentMethodServiceImpl implements IPaymentMethodService {

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

	@Override
	public void updateLimits(Long id, Float dailyLimit, Float transactionLimit) {
		PaymentMethod paymentMethod = repo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No existe el método de pago con ID: " + id));

		paymentMethod.setDailyLimit(dailyLimit);
		paymentMethod.setTransactionLimit(transactionLimit);

		repo.save(paymentMethod);
	}
}
