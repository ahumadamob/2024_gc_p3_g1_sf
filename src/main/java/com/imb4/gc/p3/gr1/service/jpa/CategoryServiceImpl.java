package com.imb4.gc.p3.gr1.service.jpa;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb4.gc.p3.gr1.entity.Category;
import com.imb4.gc.p3.gr1.repository.CategoryRepository;
import com.imb4.gc.p3.gr1.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository repo;
	
	@Override
	public List<Category> getAll() {
		return repo.findAll();
	}

	@Override
	public Category getById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Category save(Category category) {
		return repo.save(category);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public boolean exists(Long id) {
		return id == null ? false : repo.existsById(id);
	}

	@Override
	public List<Category> contarPorId() {
		return repo.countById();
	}

	@Override
	public List<Category> encontarPorNombre(String nombre) {
		return repo.findByNombreCategoryLike(nombre);
	}

	@Override
	public List<Category> encontarPorDescripcion(String nombre) {
		return repo.findByDescripcionLike(nombre);
	}

	@Override
	public List<Category> encontarPorFechaCreacion(Date fecha) {
		return repo.findByFechaCreacionEquals(fecha);
	}

	@Override
	public List<Category> encontarPorFechaCreacionIntervalo(Date inicio, Date fin) {
		return repo.findByFechaCreacionBetween(inicio, fin);
	}

	@Override
	public List<Category> encontarPorFechaActualizacion(Date fecha) {
		return repo.findByFechaActualizacionEquals(fecha);
	}

	@Override
	public List<Category> encontarPorFechaActualizacionIntervalo(Date inicio, Date fin) {
		return repo.findByFechaActualizacionBetween(inicio, fin);
	}
	
}
