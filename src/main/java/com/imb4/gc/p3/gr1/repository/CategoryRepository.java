package com.imb4.gc.p3.gr1.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb4.gc.p3.gr1.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> countById();
	List<Category> findByNombreCategoryLike(String nombre);
	List<Category> findByDescripcionLike(String nombre);
	List<Category> findByFechaCreacionEquals(Date fecha);
	List<Category> findByFechaCreacionBetween(Date inicio, Date fin);
	List<Category> findByFechaActualizacionEquals(Date fecha);
	List<Category> findByFechaActualizacionBetween(Date inicio, Date fin);
}