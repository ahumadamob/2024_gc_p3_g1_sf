package com.imb4.gc.p3.gr1.service.jpa;

import java.util.List;

import com.imb4.gc.p3.gr1.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb4.gc.p3.gr1.entity.Cart;
import com.imb4.gc.p3.gr1.entity.Product;
import com.imb4.gc.p3.gr1.exceptions.ConflictException;
import com.imb4.gc.p3.gr1.repository.CartRepository;
//import com.imb4.gc.p3.gr1.entity.User;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    // Método para obtener todos los carritos
    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    // Método para obtener un carrito por su ID
    @Override
    public Cart getById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    // Método para guardar un carrito
    @Override
    public Cart save(Cart cart) {
        // Verificamos que un usuario no tenga más de un carrito activo
        if (cartRepository.countByUserAndActiveTrue(cart.getUser()) > 0) {
            throw new ConflictException("El usuario ya tiene un carrito activo.");
        }

        // Calcular el total del carrito
        float total = 0;
        if (cart.getProducts() != null) {
            for (Product product : cart.getProducts()) {
                total += product.getPrice() * product.getQuantity();
            }
        }

        cart.setTotal(total);

        // Verificamos que el total sea mayor que cero
        if (cart.getTotal() <= 0) {
            throw new ConflictException("El total del carrito debe ser mayor que cero.");
        }

        return cartRepository.save(cart);
    }

    // Método para realizar el checkout de un carrito
    @Override
    public Cart checkout(Cart cart) {

        // Verificar que el carrito tenga productos
        if (cart.getProducts() == null || cart.getProducts().isEmpty()) {
            throw new ConflictException("El carrito no tiene productos para procesar.");
        }

        // Verificar que haya suficiente stock
        for (Product product : cart.getProducts()) {
            if (product.getStock() < product.getQuantity()) {
                throw new ConflictException("No hay suficiente stock para el producto: " + product.getName());
            }
        }

        // Verificar que el total sea positivo
        if (!(cart.getTotal() > 0)) {
            throw new ConflictException("El total del carrito tiene que ser un número positivo.");
        }

        // Verificar que el carrito tenga una dirección de envío asociada
        if (cart.getPurchaseOrder() == null || cart.getPurchaseOrder().getAddress() == null) {
            throw new ConflictException("La dirección de envío es obligatoria.");
        }

        // Verificar que el carrito tenga un usuario asociado
        if (cart.getUser() == null) {
            throw new ConflictException("El usuario no está asociado al carrito.");
        }

        // Verificar que el carrito tenga un método de pago
        if (cart.getPaymentMethod() == null) {
            throw new ConflictException("El método de pago es obligatorio.");
        }

        // Cambiar el estado de la orden de compra a COMPLETED
        cart.getPurchaseOrder().setState("COMPLETED");

        // Actualizar el stock de los productos
        for (Product product : cart.getProducts()) {
            int newStock = product.getStock() - product.getQuantity();
            product.setStock(newStock);
        }

        // Guardar el carrito después de realizar el checkout
        return cartRepository.save(cart);
    }

    // Método para eliminar un carrito
    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    // Verificar si un carrito existe
    @Override
    public boolean exists(Long id) {
        return cartRepository.existsById(id);
    }

    // Buscar carritos por un rango de total
    @Override
    public List<Cart> getByTotal(float start, float end) {
        return cartRepository.findByTotalBetween(start, end);
    }

    // Método para actualizar la cantidad de un producto en el carrito (aún no implementado)
    @Override
    public Cart updateProductQuantity(Long cartId, Long productId, int quantity) {
        return null;
    }
}
