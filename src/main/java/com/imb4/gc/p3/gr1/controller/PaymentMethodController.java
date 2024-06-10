package com.imb4.gc.p3.gr1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb4.gc.p3.gr1.service.IPaymentMethodService;
import com.imb4.gc.p3.gr1.util.APIResponse;
import com.imb4.gc.p3.gr1.util.ResponseUtil;
import com.imb4.gc.p3.gr1.entity.PaymentMethod;

@RestController
@RequestMapping(path="/api/paymentMethod")
public class PaymentMethodController {

	@Autowired
	private IPaymentMethodService paymentMethodService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<PaymentMethod>>> getAllPaymentMethod() {
		List<PaymentMethod> paymentMethods = paymentMethodService.getAll();
		return paymentMethods.isEmpty() ? ResponseUtil.notFound("No se encontraron metodos de pago") : 
			ResponseUtil.success(paymentMethods) ;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<APIResponse<PaymentMethod>> getPaymentMethodById(@PathVariable("id") Long id){
		return paymentMethodService.exists(id) ? ResponseUtil.success(paymentMethodService.getById(id)) :
			ResponseUtil.notFound("No se encontró el metodo de pago con el id: ", id);
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<PaymentMethod>> createPaymentMethod(@RequestBody PaymentMethod paymentMethod){
		return paymentMethodService.exists(paymentMethod.getId()) ? 
				ResponseUtil.badRequest("Ya existe un metodo de pago con el id: ", paymentMethod.getId()) :
					ResponseUtil.success(paymentMethodService.save(paymentMethod));
	}
	
	@PutMapping
	public ResponseEntity<APIResponse<PaymentMethod>> updatePaymentMethod(@RequestBody PaymentMethod paymentMethod){
		return paymentMethodService.exists(paymentMethod.getId()) ? 
				ResponseUtil.success(paymentMethodService.save(paymentMethod)) :
					ResponseUtil.badRequest("No existe un metodo de pago con id ", paymentMethod.getId());
	}
	
	@DeleteMapping
	public ResponseEntity<APIResponse<PaymentMethod>> deletePaymentMethod(@PathVariable("id") Long id){
		if( paymentMethodService.exists(id) ) {
			paymentMethodService.delete(id);
			return ResponseUtil.badRequest("Se eliminó el metodo de pago con el id: ", id); 
		}else {
			return ResponseUtil.badRequest("No se encontró el metodo de pago con el id: ", id);
		}
	}
}
