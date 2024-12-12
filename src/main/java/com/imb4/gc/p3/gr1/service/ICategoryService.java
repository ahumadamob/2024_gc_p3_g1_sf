package com.imb4.gc.p3.gr1.service;

import java.sql.Date;
import java.util.List;

import com.imb4.gc.p3.gr1.entity.Category;

public interface ICategoryService {
    List<Category> getAll();

    Category getById(Long id);

    Category save(Category category);

    void delete(Long id);

    boolean exists(Long id);
    
    List<Category> encontarPorNombre(String nombre);
    
    List<Category> encontarPorDescripcion(String nombre);
    
    List<Category> encontarPorFechaCreacion(Date fecha);
    
    List<Category> encontarPorFechaCreacionIntervalo(Date inicio, Date fin);
    
    List<Category> encontarPorFechaActualizacion(Date fecha);
    
    List<Category> encontarPorFechaActualizacionIntervalo(Date inicio, Date fin);
    
    int contarProductos(Category category);
}
