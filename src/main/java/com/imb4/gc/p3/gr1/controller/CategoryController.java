package com.imb4.gc.p3.gr1.controller;

import java.sql.Date;
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

import com.imb4.gc.p3.gr1.entity.Category;
import com.imb4.gc.p3.gr1.entity.Rating;
import com.imb4.gc.p3.gr1.exceptions.ConflictException;
import com.imb4.gc.p3.gr1.exceptions.ErrorResponse;
import com.imb4.gc.p3.gr1.exceptions.ResourceNotFoundException;
import com.imb4.gc.p3.gr1.util.APIResponse;
import com.imb4.gc.p3.gr1.util.ResponseUtil;

import jakarta.validation.Valid;

import com.imb4.gc.p3.gr1.service.ICategoryService;

@RestController
@RequestMapping(path="/api/category")
public class CategoryController {

	@Autowired
	private ICategoryService service;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Category>>> getAllCategories() {
		List<Category> categories = service.getAll();
		return categories.isEmpty() ? ResponseUtil.notFound("No se encontraron categorías") :
			ResponseUtil.success(categories);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<APIResponse<Category>> getCategoryById(@PathVariable("id") Long id) {
		return service.exists(id) ? ResponseUtil.success(service.getById(id)) :
			ResponseUtil.notFound("No se encontró la categoría con el id {0}", id);
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Category>> createCategory(@Valid @RequestBody Category category, @PathVariable Long id) {
		return service.exists(category.getId()) ? ResponseUtil.badRequest("Ya existe una categoría con la id {0}", category.getId()) :
			ResponseUtil.success(service.save(category));
	}
	
	//Final
	@PostMapping("nuevo")
	public ResponseEntity<APIResponse<Category>> createNewCategory(@RequestBody Category category) {
		if (category.getId() != null && service.exists(category.getId())) {
            return ResponseUtil.badRequest("El rating con el ID " + category.getId() + " ya existe");
        }
		Date fecha = new Date(2024, 01, 01); 
		if (category.getNombre_category() != null) {
			return ResponseUtil.badRequest("Error de validación. El nombre no puede estar vacío");
		}
		if (category.getDescripcion() != null) {
			return ResponseUtil.badRequest("Error de validación. La descripción no puede estar vacía");
		}
		if (category.getProducts().isEmpty()) {
			return ResponseUtil.badRequest("Error de validación. La categoría debe tener productos");
		}
		if (service.contarProductos(category) > 0) {
			return ResponseUtil.badRequest("Error de validación. Esta categoría ya tiene productos y no puede ser creada");
		}
		if (category.getFecha_creacion() < fecha) {
			return ResponseUtil.badRequest("Error de validación. Esta categoría no puede existir antes del 2024");
		}
		
        Category newCategory = service.save(category);
        return ResponseUtil.success(newCategory);
    }
	
	@PutMapping("{id}")
	public ResponseEntity<APIResponse<Category>> updateCategory(@Valid @RequestBody Category category, @PathVariable("id") Long id) {
		category.setId(id);
		return service.exists(id) ? ResponseUtil.success(service.save(category)) :
			ResponseUtil.badRequest("No existe una categoría con la id {0}", id);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<Category>> deleteCategory(@PathVariable("id") Long id) {
		if (service.exists(id)) {
			service.delete(id);
			return ResponseUtil.successDeleted("Se eliminó la categoría con la id {0}", id);
		}else {
			return ResponseUtil.badRequest("No existe una categoría con la id {0}", id);
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
