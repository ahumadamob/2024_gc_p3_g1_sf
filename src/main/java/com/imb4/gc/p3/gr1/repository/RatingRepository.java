package com.imb4.gc.p3.gr1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.imb4.gc.p3.gr1.entity.Rating;


@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	List<Rating> findByProductId(Long productId);
  List<Rating> findByUser(String user);
  /*List<Rating> findByProduct(String product);*/
  List<Rating> findByProductName(String name);
  List<Rating> findByRating(int rating);
  List<Rating> findByOpinionContaining(String keyword);
 Optional<Rating> findById(Long id);
}