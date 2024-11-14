package com.imb4.gc.p3.gr1.service;

import java.util.List;

import com.imb4.gc.p3.gr1.entity.Product;

public interface IProductService {
    List<Product> getAll();

    Product getById(Long id);

    Product save(Product product);

    void delete(Long id);

    boolean exists(Long id);
    
    public List<Product> encontrarPorNombre(String name);
    
    public List<Product> precioMenorIgualA(float price);
    
    public List<Product> precioMayorIgualA(float price);

    void markAsFeatured(Long id, Boolean featured);
}