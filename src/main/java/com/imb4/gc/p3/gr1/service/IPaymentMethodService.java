package com.imb4.gc.p3.gr1.service;

import java.util.List;

import com.imb4.gc.p3.gr1.entity.PaymentMethod;

public interface IPaymentMethodService {
    public List<PaymentMethod> getAll();
    public PaymentMethod getById(Long id);
    public PaymentMethod save(PaymentMethod paymentMethod);
    public void delete(Long id);
    public boolean exists(Long id);
}