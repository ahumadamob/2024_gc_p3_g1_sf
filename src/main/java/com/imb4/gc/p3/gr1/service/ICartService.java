package com.imb4.gc.p3.gr1.service;

import java.util.List;
import com.imb4.gc.p3.gr1.entity.Cart;

public interface ICartService {
    List<Cart> getAll();
    Cart getById(Long id);
    Cart save(Cart cart);
    void delete(Long id);
    boolean exists(Long id);
    List<Cart> getByTotal(float start, float end);
    Cart updateProductQuantity(Long cartId, Long productId, int quantity);
}