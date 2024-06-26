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

import com.imb4.gc.p3.gr1.entity.Product;
import com.imb4.gc.p3.gr1.service.IProductService;
import com.imb4.gc.p3.gr1.util.APIResponse;
import com.imb4.gc.p3.gr1.util.ResponseUtil;

@RestController
@RequestMapping(path="/api/product")
public class ProductController {
	@Autowired
	private IProductService productService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Product>>> getProduct(){
		List<Product> products = productService.getAll();
		return products.isEmpty()? ResponseUtil.notFound("No se encontraron productos") :
			ResponseUtil.success(products);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<APIResponse<Product>> getProductById(@PathVariable("id") Long id){
		return productService.exists(id)? ResponseUtil.success(productService.getById(id)) :
			ResponseUtil.notFound("No se encontró producto con id {0}", id);
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Product>> saveProduct(@RequestBody Product product){
		return productService.exists(product.getId())? ResponseUtil.badRequest("Ya existe un producto con id {0}", product.getId()) :
			ResponseUtil.success(productService.save(product));
	}
	
	@PutMapping
	public ResponseEntity<APIResponse<Product>> updateProduct(@RequestBody Product product){
		return productService.exists(product.getId())? ResponseUtil.success(productService.save(product)) :
			ResponseUtil.badRequest("No existe un producto con id {0}", product.getId());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<Product>> deleteProduct(@PathVariable("id") Long id){
		if(productService.exists(id)) {
			productService.delete(id);
			return ResponseUtil.successDeleted("Se eliminó el producto con id {0}", id);
		}else {
			return ResponseUtil.badRequest("No se encontró el producto con id {0}", id);
		}
	}
}