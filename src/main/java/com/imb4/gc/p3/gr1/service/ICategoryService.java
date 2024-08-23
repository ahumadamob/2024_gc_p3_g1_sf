package com.imb4.gc.p3.gr1.service;

import java.util.List;

import com.imb4.gc.p3.gr1.entity.Category;

public interface ICategoryService {
    List<Category> getAll();

    Category getById(Long id);

    Category save(Category category);

    void delete(Long id);

    boolean exists(Long id);
}
