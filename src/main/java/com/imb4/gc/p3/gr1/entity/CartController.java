package com.imb4.gc.p3.gr1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.imb4.gc.p3.gr1.entity.Cart;
import com.imb4.gc.p3.gr1.service.ICartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Cart cart = cartService.getById(id);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.save(cart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cartDetails) {
        if (!cartService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        cartDetails.setId(id);
        Cart updatedCart = cartService.save(cartDetails);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        if (!cartService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        cartService.delete(id);
        return ResponseEntity.noContent().build();
    }
}