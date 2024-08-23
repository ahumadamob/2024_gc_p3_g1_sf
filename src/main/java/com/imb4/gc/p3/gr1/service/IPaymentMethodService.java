package com.imb4.gc.p3.gr1.service;

import java.util.List;

import com.imb4.gc.p3.gr1.entity.PaymentMethod;

public interface IPaymentMethodService {
    List<PaymentMethod> getAll();

    PaymentMethod getById(Long id);

    PaymentMethod save(PaymentMethod paymentMethod);

    void delete(Long id);

    boolean exists(Long id);
}