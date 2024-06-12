package com.imb4.gc.p3.gr1.entity;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb4.gc.p3.gr1.entity.Cart;
import com.imb4.gc.p3.gr1.repository.CartRepository;
import com.imb4.gc.p3.gr1.service.ICartService;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getById(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.orElse(null);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return cartRepository.existsById(id);
    }
}
