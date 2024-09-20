package com.imb4.gc.p3.gr1.service;

import com.imb4.gc.p3.gr1.entity.Rating;
import com.imb4.gc.p3.gr1.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements IRatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getById(Long id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        return rating.orElse(null);
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void delete(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return ratingRepository.existsById(id);
    }
}
