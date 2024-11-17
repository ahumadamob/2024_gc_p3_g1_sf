package com.imb4.gc.p3.gr1.service.jpa;

import com.imb4.gc.p3.gr1.entity.Rating;
import com.imb4.gc.p3.gr1.exception.RatingNotFoundException;
import com.imb4.gc.p3.gr1.repository.RatingRepository;
import com.imb4.gc.p3.gr1.service.IRatingService;
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
        return ratingRepository.findByProductName(product);
    }

	@Override
	public Rating approve(Long id) {
		Optional<Rating> optionalRating = ratingRepository.findById(id);
	    if (optionalRating.isPresent()) {
	        Rating rating = optionalRating.get();
	        rating.setApproved(true);
	        return ratingRepository.save(rating);
	    } else {
	        // Puedes manejar el caso en que el Rating no se encuentra
	        throw new RatingNotFoundException("Rating no encontrado con el id: " + id);
	    }
	}
	
	public List<Rating> getRatingsByProductId(Long productId) {
		return ratingRepository.findByProductId(productId);
	}

	@Override
	public Double calculateAverageNoteByProductId(Long productId) {
		List<Rating> ratings = getRatingsByProductId(productId);
        if (ratings.isEmpty()) {
            return 0.0;
        }
        return ratings.stream()
        		.mapToInt(r -> Integer.parseInt(r.getNote()))
                .average()
                .orElse(0.0);
	}
}
