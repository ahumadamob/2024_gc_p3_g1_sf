package com.imb4.gc.p3.gr1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.imb4.gc.p3.gr1.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    
}