package com.imb4.gc.p3.gr1.service;

import  com.imb4.gc.p3.gr1.entity.Rating;

import java.util.List;

public interface IRatingService {
    List<Rating> getAll();
    Rating getById(Long id);
    Rating save(Rating rating);
    void delete(Long id);
    boolean exists(Long id);
    List<Rating> getRatingsByProductId(Long productId);
    Double calculateAverageNoteByProductId(Long productId);
}
