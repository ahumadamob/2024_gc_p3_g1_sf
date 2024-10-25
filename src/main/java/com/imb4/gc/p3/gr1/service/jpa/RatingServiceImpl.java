package com.imb4.gc.p3.gr1.service.jpa;

import com.imb4.gc.p3.gr1.entity.Rating;
import com.imb4.gc.p3.gr1.repository.RatingRepository;
import com.imb4.gc.p3.gr1.service.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return ratingRepository.findById(id).orElse(null);
    }

    @Override
    public Rating save(Rating rating) {
        if (rating.getRating() < 1 || rating.getRating() > 5) {
            throw new IllegalArgumentException("El rating debe estar entre 1 y 5");
        }
        return ratingRepository.save(rating);
    }

    @Override
    public void delete(Long id) {
        try {
            ratingRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el rating con ID " + id, e);
        }
    }

    @Override
    public boolean exists(Long id) {
        return id != null && ratingRepository.existsById(id);
    }

    @Override
    public List<Rating> findByUser(String user) {
        return ratingRepository.findByUser(user);
    }

    @Override
    public List<Rating> findByProduct(String product) {
        return ratingRepository.findByProduct(product);
    }
}
