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

import com.imb4.gc.p3.gr1.entity.PurchaseOrder;
import com.imb4.gc.p3.gr1.service.IPurchaseOrderService;
import com.imb4.gc.p3.gr1.util.APIResponse;
import com.imb4.gc.p3.gr1.util.ResponseUtil;

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
	public ResponseEntity<APIResponse<PurchaseOrder>> getPurchaseOrderById(@PathVariable("id") Long id){
		return purchaseOrderService.exists(id)? ResponseUtil.success(purchaseOrderService.getById(id)) :
			ResponseUtil.notFound("No se encontró la órden de compra con id {0}", id);
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<PurchaseOrder>> savePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder){
		return purchaseOrderService.exists(purchaseOrder.getId())? ResponseUtil.badRequest("Ya existe una órden de compra con id {0}", purchaseOrder.getId()) :
			ResponseUtil.success(purchaseOrderService.save(purchaseOrder));
	}
	
	@PutMapping
	public ResponseEntity<APIResponse<PurchaseOrder>> updatePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder){
		return purchaseOrderService.exists(purchaseOrder.getId())? ResponseUtil.success(purchaseOrderService.save(purchaseOrder)) :
			ResponseUtil.badRequest("No existe una órden de compra con id {0}", purchaseOrder.getId());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<PurchaseOrder>> deletePurchaseOrder(@PathVariable("id") Long id){
		if(purchaseOrderService.exists(id)) {
			purchaseOrderService.delete(id);
			return ResponseUtil.successDeleted("Se eliminó la órden de compra con id {0}", id);
		}else {
			return ResponseUtil.badRequest("No se encontró la órden de compra con id {0}", id);
		}
	}
}
