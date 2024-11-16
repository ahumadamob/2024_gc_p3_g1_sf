package com.imb4.gc.p3.gr1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.imb4.gc.p3.gr1.dto.UpdateLimitsDTO;
import com.imb4.gc.p3.gr1.service.IPaymentMethodService;
import com.imb4.gc.p3.gr1.util.APIResponse;
import com.imb4.gc.p3.gr1.util.ResponseUtil;
import jakarta.validation.Valid;
import com.imb4.gc.p3.gr1.entity.PaymentMethod;
import com.imb4.gc.p3.gr1.exceptions.ConflictException;
import com.imb4.gc.p3.gr1.exceptions.ErrorResponse;
import com.imb4.gc.p3.gr1.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(path = "/api/paymentMethod")
public class PaymentMethodController {

	@Autowired
	private IPaymentMethodService paymentMethodService;

	// Obtener todos los métodos de pago
	@GetMapping
	public ResponseEntity<APIResponse<List<PaymentMethod>>> getAllPaymentMethod() {
		List<PaymentMethod> paymentMethods = paymentMethodService.getAll();
		return paymentMethods.isEmpty() ? ResponseUtil.notFound("No se encontraron métodos de pagos")
				: ResponseUtil.success(paymentMethods);
	}

	// Obtener un método de pago por ID
	@GetMapping("{id}")
	public ResponseEntity<APIResponse<PaymentMethod>> getPaymentMethodById(@PathVariable("id") Long id) {
		if (!paymentMethodService.exists(id)) {
			throw new ResourceNotFoundException("No se encontró el método de pago con id " + id);
		}
		PaymentMethod paymentMethod = paymentMethodService.getById(id);
		return ResponseUtil.success(paymentMethod);
	}

	// Crear un nuevo método de pago
	@PostMapping
	public ResponseEntity<APIResponse<PaymentMethod>> createPaymentMethod(@Valid @RequestBody PaymentMethod paymentMethod) {
		if (paymentMethodService.exists(paymentMethod.getId())) {
			throw new ConflictException("Ya existe un método de pago");
		}
		return ResponseUtil.success(paymentMethodService.save(paymentMethod));
	}

	// Actualizar un método de pago existente
	@PutMapping
	public ResponseEntity<APIResponse<PaymentMethod>> updatePaymentMethod(@Valid @RequestBody PaymentMethod paymentMethod) {
		if (!paymentMethodService.exists(paymentMethod.getId())) {
			throw new ResourceNotFoundException("No existe un método de pago con id " + paymentMethod.getId());
		}
		return ResponseUtil.success(paymentMethodService.save(paymentMethod));
	}

	// Eliminar un método de pago por ID
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<PaymentMethod>> deletePaymentMethod(@PathVariable("id") Long id) {
		if (paymentMethodService.exists(id)) {
			paymentMethodService.delete(id);
			return ResponseUtil.successDeleted("Se eliminó el método de pago con el id: ", id);
		} else {
			throw new ResourceNotFoundException("No se encontró el método de pago con el id: " + id);
		}
	}

	// Buscar métodos de pago por nombre
	@GetMapping("/name")
	public ResponseEntity<APIResponse<List<PaymentMethod>>> getPaymentMethodsByName(@RequestParam String name) {
		List<PaymentMethod> paymentMethods = paymentMethodService.findByName(name);
		return paymentMethods.isEmpty()
				? ResponseUtil.notFound("No se encontraron métodos de pago con el nombre: " + name)
				: ResponseUtil.success(paymentMethods);
	}

	// Nueva función: Actualizar límites de un método de pago
	@PutMapping("/{id}/updateLimits")
	public ResponseEntity<APIResponse<PaymentMethod>> updatePaymentMethodLimits(
			@PathVariable("id") Long id,
			@Valid @RequestBody UpdateLimitsDTO updateLimitsDTO) {
		if (!paymentMethodService.exists(id)) {
			throw new ResourceNotFoundException("No existe el método de pago con ID: " + id);
		}

		paymentMethodService.updateLimits(id, updateLimitsDTO.getDailyLimit(), updateLimitsDTO.getTransactionLimit());
		PaymentMethod updatedPaymentMethod = paymentMethodService.getById(id);

		return ResponseUtil.success(updatedPaymentMethod);
	}

	// Manejadores de excepciones
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
