package com.imb4.gc.p3.gr1.service;

import java.util.List;

import com.imb4.gc.p3.gr1.entity.Category;

public interface ICategoryService {
	public List<Category> getAll();
	public Category getById(Long id);
	public Category save(Category category);
	public void delete(Long id);
	boolean exists(Long id);
}
