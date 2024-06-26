package com.imb4.gc.p3.gr1.service;

import java.util.List;

import com.imb4.gc.p3.gr1.entity.Product;

public interface IProductService {
    public List<Product> getAll();
    public Product getById(Long id);
    public Product save(Product product);
    public void delete(Long id);
    public boolean exists(Long id);
}