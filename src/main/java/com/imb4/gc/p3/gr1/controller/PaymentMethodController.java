package com.imb4.gc.p3.gr1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imb4.gc.p3.gr1.service.IPaymentMethodService;
import com.imb4.gc.p3.gr1.util.APIResponse;
import com.imb4.gc.p3.gr1.util.ResponseUtil;

import jakarta.validation.Valid;

import com.imb4.gc.p3.gr1.entity.PaymentMethod;
import com.imb4.gc.p3.gr1.exceptions.ConflictException;
import com.imb4.gc.p3.gr1.exceptions.ErrorResponse;
import com.imb4.gc.p3.gr1.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(path="/api/paymentMethod")
public class PaymentMethodController {

	@Autowired
	private IPaymentMethodService paymentMethodService;
	
	
	@GetMapping
	public ResponseEntity<APIResponse<List<PaymentMethod>>> getAllPaymentMethod(){
		List<PaymentMethod> paymentMethods = paymentMethodService.getAll();
		return paymentMethods.isEmpty()? ResponseUtil.notFound("No se encontraron metodos de pagos") :
			ResponseUtil.success(paymentMethods);
	}
	

	@GetMapping("{id}")
	public ResponseEntity<APIResponse<PaymentMethod>> getPaymentMethodById(@PathVariable("id") Long id){
		if (!paymentMethodService.exists(id)) {
	        throw new ResourceNotFoundException("No se encontró el metodo de pago con id " + id);
		}
		PaymentMethod paymentMethod = paymentMethodService.getById(id);
	    return ResponseUtil.success(paymentMethod);
	}
	


	@PostMapping
	public ResponseEntity<APIResponse<PaymentMethod>> createPaymentMethod(@Valid @RequestBody PaymentMethod paymentMethod){
		if (paymentMethodService.exists(paymentMethod.getId())) {
	        throw new ConflictException("Ya existe un metodo de pago");
	    }
	    return ResponseUtil.success(paymentMethodService.save(paymentMethod));
	}
	
	
	
	@PutMapping
	public ResponseEntity<APIResponse<PaymentMethod>> PaymentMethod(@Valid @RequestBody PaymentMethod paymentMethod){
		if (!paymentMethodService.exists(paymentMethod.getId())) {
	        throw new ResourceNotFoundException("No existe un metodo de pago con id" + paymentMethod.getId());
	    }
	    return ResponseUtil.success(paymentMethodService.save(paymentMethod));
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<PaymentMethod>> deletePaymentMethod(@PathVariable("id") Long id){
		if(paymentMethodService.exists(id)) {
			paymentMethodService.delete(id);
			return ResponseUtil.successDeleted("Se eliminó el metodo de pago con el id: ", id);
		}else {
			throw new ResourceNotFoundException("No se encontró el metodo de pago con el id: " + id);
		}
	}
	

	
	@GetMapping("/name")
	public ResponseEntity<APIResponse<List<PaymentMethod>>> getPaymentMethodsByName(@RequestParam String name){
		List<PaymentMethod> paymentMethods = paymentMethodService.findByName(name);
		return paymentMethods.isEmpty()? ResponseUtil.notFound("No se encontraron métodos de pago con el nombre: " + name) :
			ResponseUtil.success(paymentMethods);
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
