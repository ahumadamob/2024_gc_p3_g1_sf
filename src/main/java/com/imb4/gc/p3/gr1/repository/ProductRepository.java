package com.imb4.gc.p3.gr1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imb4.gc.p3.gr1.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{//name price
	public List<Product> findByNameLike(String name);
	public List<Product> findByPriceLessThanEqual(float price);
	public List<Product> findByPriceGreaterThanEqual(float price);
	@Modifying
    @Query("UPDATE Product p SET p.featured = :featured WHERE p.id = :id")
    void updateFeatured(@Param("id") Long id, @Param("featured") Boolean featured);
}
