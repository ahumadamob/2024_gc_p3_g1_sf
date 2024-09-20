package com.imb4.gc.p3.gr1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb4.gc.p3.gr1.entity.Category;
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
	public ResponseEntity<APIResponse<Category>> createCategory(@Valid @RequestBody Category category, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseUtil.badRequest("Error de validación al crear categoría");}
		return service.exists(category.getId()) ? ResponseUtil.badRequest("Ya existe una categoría con la id {0}", category.getId()) :
			ResponseUtil.success(service.save(category));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<APIResponse<Category>> updateCategory(@Valid @RequestBody Category category, @PathVariable("id") Long id, BindingResult result) {
		category.setId(id);
		if (result.hasErrors()) {
			return ResponseUtil.badRequest("Error de validación al actualizar categoría");}
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
}
