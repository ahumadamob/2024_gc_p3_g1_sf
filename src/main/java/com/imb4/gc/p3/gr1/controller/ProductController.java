package com.imb4.gc.p3.gr1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb4.gc.p3.gr1.entity.DestacarRequest;
import com.imb4.gc.p3.gr1.entity.Product;
import com.imb4.gc.p3.gr1.exceptions.ConflictException;
import com.imb4.gc.p3.gr1.exceptions.ErrorResponse;
import com.imb4.gc.p3.gr1.exceptions.ResourceNotFoundException;
import com.imb4.gc.p3.gr1.service.IProductService;
import com.imb4.gc.p3.gr1.util.APIResponse;
import com.imb4.gc.p3.gr1.util.ResponseUtil;

import jakarta.validation.Valid;

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
		if ( !productService.exists(id) ) {
			throw new ResourceNotFoundException("No se encontró producto con id " + id);
		}
		Product product = productService.getById(id);
		return ResponseUtil.success(product);
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Product>> saveProduct(@Valid @RequestBody Product product, BindingResult result){
		if ( productService.exists( product.getId() ) ) {
			throw new ConflictException("Ya existe un producto con id " + product.getId());
        }
		return ResponseUtil.success(productService.save(product));
	}
	
	@PutMapping
	public ResponseEntity<APIResponse<Product>> updateProduct(@Valid @RequestBody Product product){
		if ( !productService.exists( product.getId() ) ) {
			throw new ResourceNotFoundException( "No existe un producto con id {0}" + product.getId() );
		}
		return ResponseUtil.success( productService.save(product) );
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<Product>> deleteProduct(@PathVariable("id") Long id){
		if(productService.exists(id)) {
			productService.delete(id);
			return ResponseUtil.successDeleted("Se eliminó el producto con id {0}", id);
		}else {
			throw new ResourceNotFoundException( "No se encontró el producto con id " + id);
		}
	}
	
	@GetMapping("/pornombre/{nam}")
	public ResponseEntity<List<Product>> buscarProductosPorNombre(@PathVariable("nam") String name){
		List<Product> listado = productService.encontrarPorNombre("%" + name + "%");
		if(listado.isEmpty()) {
			return new ResponseEntity<>(listado, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(listado, HttpStatus.OK); 
		}		
	}
	
	@GetMapping("/menorIgualA/{pri}")
	public ResponseEntity<List<Product>> buscarProductosPorPricioMenorIgual(@PathVariable("pri") float price){
		List<Product> listado = productService.precioMenorIgualA(price);
		if(listado.isEmpty()) {
			return new ResponseEntity<>(listado, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(listado, HttpStatus.OK); 
		}		
	}
	
	@GetMapping("/mayorIgualA/{pri}")
	public ResponseEntity<List<Product>> buscarProductosPorPrecioMayorIgual(@PathVariable("pri") float price){
		List<Product> listado = productService.precioMayorIgualA(price);
		if(listado.isEmpty()) {
			return new ResponseEntity<>(listado, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(listado, HttpStatus.OK); 
		}		
	}
	
	@PutMapping("/{id}/destacar")
	public ResponseEntity<String> marcarComoDestacado(@PathVariable Long id, @RequestBody DestacarRequest destacarRequest) {
	    try {
	        productService.marcarComoDestacado(id, destacarRequest.isDestacado());
	        return ResponseEntity.ok("El estado de destacado del producto se ha actualizado correctamente.");
	    } catch (ResourceNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el producto");
	    }
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