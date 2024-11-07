package com.imb4.gc.p3.gr1.service.jpa;

import java.util.List;

import com.imb4.gc.p3.gr1.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb4.gc.p3.gr1.entity.Cart;
import com.imb4.gc.p3.gr1.entity.Product;
import com.imb4.gc.p3.gr1.exceptions.ConflictException;
import com.imb4.gc.p3.gr1.repository.CartRepository;

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
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
	public Cart checkout(Cart cart) {
    	
        if (cart.getProducts() == null || cart.getProducts().isEmpty()) {
            throw new ConflictException("El carrito no tiene productos para procesar.");
        }

        for (Product product : cart.getProducts()) {
            if (product.getStock() < product.getQuantity()) {
                throw new ConflictException("No hay suficiente stock para el producto: " + product.getName());
                }
        }

        if (!(cart.getTotal() > 0)) {
            throw new ConflictException("El total del carrito tiene que ser un numero positivo.");
        }

        if (cart.getPurchaseOrder() == null || cart.getPurchaseOrder().getAddress() == null) {
            throw new ConflictException("La dirección de envío es obligatoria.");
        }

        if (cart.getUser() == null) {
            throw new ConflictException("El usuario no está asociado al carrito.");
        }

        if (cart.getPaymentMethod() == null) {
            throw new ConflictException("El método de pago es obligatorio.");
        }

        cart.getPurchaseOrder().setState("COMPLETED");

        for (Product product : cart.getProducts()) {
            int newStock = product.getStock() - product.getQuantity();
            product.setStock(newStock);
        }

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
	
}