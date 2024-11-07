package com.imb4.gc.p3.gr1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.imb4.gc.p3.gr1.entity.Cart;
import com.imb4.gc.p3.gr1.entity.CartProduct;
import com.imb4.gc.p3.gr1.exceptions.ConflictException;
import com.imb4.gc.p3.gr1.exceptions.ErrorResponse;
import com.imb4.gc.p3.gr1.exceptions.ResourceNotFoundException;
import com.imb4.gc.p3.gr1.service.ICartService;
import com.imb4.gc.p3.gr1.util.APIResponse;
import com.imb4.gc.p3.gr1.util.ResponseUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/api/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping
	public ResponseEntity<APIResponse<List<Cart>>> getCarts(){
		List<Cart> carts = cartService.getAll();
		return carts.isEmpty()? ResponseUtil.notFound("No se encontraron carritos") :
			ResponseUtil.success(carts);
	}
    
    @GetMapping("{id}")
	public ResponseEntity<APIResponse<Cart>> getCartById(@PathVariable("id") Long id) {
		if (!cartService.exists(id)) {
	        throw new ResourceNotFoundException("No se encontró el carrito con id " + id);
		}
	    Cart cart = cartService.getById(id);
	    return ResponseUtil.success(cart);
	}
    
    @PostMapping
	public ResponseEntity<APIResponse<Cart>> saveCart(@Valid @RequestBody Cart cart){
		if (cartService.exists(cart.getId())) {
	        throw new ConflictException("El carrito ya existe.");
	    }
	    return ResponseUtil.success(cartService.save(cart));
	}
    
    @PostMapping("{id}/checkout")
   	public ResponseEntity<APIResponse<Cart>> checkoutCart(@PathVariable("id") Long id){
    	if (!cartService.exists(id)) {
	        throw new ResourceNotFoundException("No se encontró el carrito con id " + id);
		}
	    Cart cart = cartService.getById(id);
   	    return ResponseUtil.success(cartService.checkout(cart));
   	}

    @PutMapping
	public ResponseEntity<APIResponse<Cart>> updateCart(@Valid @RequestBody Cart cart){
		if (!cartService.exists(cart.getId())) {
	        throw new ResourceNotFoundException("No existe un carrito con id " + cart.getId());
	    }
	    return ResponseUtil.success(cartService.save(cart));
	}
    
    @DeleteMapping("{id}")
	public ResponseEntity<APIResponse<Cart>> deleteCart(@PathVariable("id") Long id){
		if(cartService.exists(id)) {
			cartService.delete(id);
			return ResponseUtil.successDeleted("Se eliminó el carrito con id {0}", id);
		}else {
			throw new ResourceNotFoundException("No existe un carrito con id " + id);
		}
	}
    
    @GetMapping("/total")
	public ResponseEntity<APIResponse<List<Cart>>> getCartsByTotal(@RequestParam float start, float end){
		List<Cart> carts = cartService.getByTotal(start, end);
		return carts.isEmpty()? ResponseUtil.notFound("No se encontraron carritos entre esos valores.") :
			ResponseUtil.success(carts);
	}
    
    @PutMapping("/{cartId}/products/{productId}")
    public ResponseEntity<APIResponse<Cart>> updateProductQuantity(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId, @Valid @RequestBody CartProduct cartProduct) {
    	/*
    	try {
    		Cart cart = cartService.updateProductQuantity(cartId, productId, cartProduct.getQuantity());
    		return ResponseUtil.success(cartService.save(cart));
    	} catch (ResourceNotFoundException ex) {
    		throw new ResourceNotFoundException(ex.getMessage());
    	}
    	*/
    	
    	Cart cart = cartService.updateProductQuantity(cartId, productId, cartProduct.getQuantity());
    	return ResponseUtil.success(cartService.save(cart));
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Recurso no encontrado", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflict(ConflictException ex) {
    	ErrorResponse errorResponse = new ErrorResponse("El recurso ya existe", ex.getMessage());
    	return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("Error interno del servidor", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}