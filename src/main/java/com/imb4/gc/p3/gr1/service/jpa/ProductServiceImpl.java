package com.imb4.gc.p3.gr1.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb4.gc.p3.gr1.entity.Product;
import com.imb4.gc.p3.gr1.repository.ProductRepository;
import com.imb4.gc.p3.gr1.service.IProductService;

import jakarta.persistence.EntityNotFoundException;
@Service
public class ProductServiceImpl implements IProductService{
	@Autowired
	private ProductRepository repository;
	
	@Override
	public List<Product> getAll() {
		return repository.findAll();
	}

	@Override
	public Product getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Product save(Product product) {
		return repository.save(product);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public boolean exists(Long id) {
		return repository.existsById(id) ;
	}

	@Override
	public List<Product> encontrarPorNombre(String name) {
		return repository.findByNameLike(name);
	}

	@Override
	public List<Product> precioMenorIgualA(float price) {
		return repository.findByPriceLessThanEqual(price);
	}

	@Override
	public List<Product> precioMayorIgualA(float price) {
		return repository.findByPriceGreaterThanEqual(price);
	}
	@Override
    public void marcarComoDestacado(Long id, boolean destacado) {
        Product product = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        
        product.setDestacado(destacado);
        repository.save(product);
    }
}
