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
import com.imb4.gc.p3.gr1.entity.PurchaseOrder;
import com.imb4.gc.p3.gr1.exceptions.ResourceNotFoundException;
import com.imb4.gc.p3.gr1.exceptions.ConflictException;
import com.imb4.gc.p3.gr1.exceptions.ErrorResponse;
import com.imb4.gc.p3.gr1.service.IPurchaseOrderService;
import com.imb4.gc.p3.gr1.util.APIResponse;
import com.imb4.gc.p3.gr1.util.ResponseUtil;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/api/purchaseOrder")
public class PurchaseOrderController {
	
	@Autowired
	private IPurchaseOrderService purchaseOrderService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<PurchaseOrder>>> getPurchaseOrders(){
		List<PurchaseOrder> purchaseOrders = purchaseOrderService.getAll();
		return purchaseOrders.isEmpty()? ResponseUtil.notFound("No se encontraron órdenes de compra") :
			ResponseUtil.success(purchaseOrders);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<APIResponse<PurchaseOrder>> getPurchaseOrderById(@PathVariable("id") Long id) {
		if (!purchaseOrderService.exists(id)) {
	        throw new ResourceNotFoundException("No se encontró la orden de compra con id " + id);
		}
	    PurchaseOrder purchaseOrder = purchaseOrderService.getById(id);
	    return ResponseUtil.success(purchaseOrder);
	}
	
	@GetMapping("/states")
	public ResponseEntity<APIResponse<List<PurchaseOrder>>> getPurchaseOrdersByState(@RequestParam String state){
		List<PurchaseOrder> purchaseOrders = purchaseOrderService.getByState(state);
		return purchaseOrders.isEmpty()? ResponseUtil.notFound("No se encontraron órdenes de compra en " + state) :
			ResponseUtil.success(purchaseOrders);
	}
	
	@GetMapping("/mayores")
	public ResponseEntity<APIResponse<List<PurchaseOrder>>> getPurchaseOrdersGreaterThan(@RequestParam double total){
		List<PurchaseOrder> purchaseOrders = purchaseOrderService.findByTotalGreaterThan(total);
		return purchaseOrders.isEmpty()? ResponseUtil.notFound("No se encontraron órdenes de compra con total mayor a " + total) :
			ResponseUtil.success(purchaseOrders);
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<PurchaseOrder>> savePurchaseOrder(@Valid @RequestBody PurchaseOrder purchaseOrder){
		if (purchaseOrderService.exists(purchaseOrder.getId())) {
	        throw new ConflictException("La órden de compra ya existe");
	    }
	    return ResponseUtil.success(purchaseOrderService.save(purchaseOrder));
	}
	
	@PutMapping
	public ResponseEntity<APIResponse<PurchaseOrder>> updatePurchaseOrder(@Valid @RequestBody PurchaseOrder purchaseOrder){
		if (!purchaseOrderService.exists(purchaseOrder.getId())) {
	        throw new ResourceNotFoundException("No existe una órden de compra con id " + purchaseOrder.getId());
	    }
	    return ResponseUtil.success(purchaseOrderService.save(purchaseOrder));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<PurchaseOrder>> deletePurchaseOrder(@PathVariable("id") Long id){
		if(purchaseOrderService.exists(id)) {
			purchaseOrderService.delete(id);
			return ResponseUtil.successDeleted("Se eliminó la órden de compra con id {0}", id);
		}else {
			throw new ResourceNotFoundException("No existe una órden de compra con id " + id);
		}
	}
	
	@PostMapping("/nueva")
	public ResponseEntity<APIResponse<PurchaseOrder>> createPurchaseOrder(@Valid @RequestBody PurchaseOrder purchaseOrder) {
	    if (purchaseOrderService.exists(purchaseOrder.getId())) {
	        throw new ConflictException("La orden de compra ya existe");
	    }
	    
	    if (purchaseOrderService.existsByState(purchaseOrder.getState())) {
	        throw new ConflictException("Ya existe  una órden de compra con ese estado");
	    }
	    
	    if (!purchaseOrder.getShippingMethod().equals("standard") && !purchaseOrder.getShippingMethod().equals("express")) {
	        throw new IllegalArgumentException("Método de envío inválido: debe ser 'standard' o 'express'");
	    }

	    //purchaseOrder.setShippingMethod(purchaseOrder.getShippingMethod());
	    purchaseOrderService.setearShippingMethod(purchaseOrder);

	    PurchaseOrder savedOrder = purchaseOrderService.save(purchaseOrder);
	    return ResponseUtil.success(savedOrder);
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
