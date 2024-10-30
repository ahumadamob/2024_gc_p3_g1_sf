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
        return ratingRepository.save(rating);
    }

    @Override
    public void delete(Long id) {
        ratingRepository.deleteById(id);

    }

    @Override
    public boolean exists(Long id) {
        return id==null ? false : ratingRepository.existsById(id);
    }

	@Override
	public Rating approve(Rating rating) {
		if (rating.getId() != null) {
			rating = ratingRepository.getById(rating.getId());
			rating.setApproved(true);
			return ratingRepository.save(rating);
		}
		return rating;
	}
}
