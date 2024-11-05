package com.imb4.gc.p3.gr1.service.jpa;

import java.util.List;
import com.imb4.gc.p3.gr1.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb4.gc.p3.gr1.entity.Cart;
import com.imb4.gc.p3.gr1.entity.CartProduct;
import com.imb4.gc.p3.gr1.entity.Product;
import com.imb4.gc.p3.gr1.exceptions.ResourceNotFoundException;
import com.imb4.gc.p3.gr1.repository.CartProductRepository;
import com.imb4.gc.p3.gr1.repository.CartRepository;
import com.imb4.gc.p3.gr1.repository.ProductRepository;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CartProductRepository cartProductRepository;

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getById(Long id) {
        return cartRepository.findById(id).orElse(null);
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

	@Override
	public List<Cart> getByTotal(float start, float end) {
		return cartRepository.findByTotalBetween(start, end);
	}

	@Override
	public Cart updateProductQuantity(Long cartId, Long productId, int quantity) {
		
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Carrito no encontrado"));
		
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
		
		CartProduct cartProduct = cartProductRepository.findByCartIdAndProductId(cartId, productId)
				.orElseThrow(() -> new ResourceNotFoundException("El producto no está en el carrito"));
		
		cartProduct.setQuantity(quantity);
		cartProductRepository.save(cartProduct);
		cart.setTotal(product.getPrice()*quantity);
		return cart;
		
		/*
		Optional<Cart> cartOpt = cartRepository.findById(cartId);
		Optional<Product> productOpt = productRepository.findById(productId);
		Optional<CartProduct> cartProductOpt = cartProductRepository.findByCartIdAndProductId(cartId, productId);
		if (cartOpt.isPresent() && productOpt.isPresent() && cartProductOpt.isPresent()) {
			Cart cart = cartOpt.get();
	        Product product = productOpt.get();
	        CartProduct cartProduct = cartProductOpt.get();
			
			cartProduct.setQuantity(quantity);
			cartProductRepository.save(cartProduct);
			cart.setTotal(product.getPrice()*quantity);
			return cart;
		} else {
			return cartOpt.orElse(null);
		}
		*/
	}
}