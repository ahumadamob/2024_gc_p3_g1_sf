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

    /**
     * Devuelve todos los ratings almacenados en la base de datos.
     * @return Lista de objetos Rating.
     */
    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    /**
     * Devuelve un Rating por su ID.
     * @param id ID del rating.
     * @return Objeto Rating o null si no se encuentra.
     */
    @Override
    public Rating getById(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }

    /**
     * Guarda o actualiza un Rating.
     * Valida que el rating esté entre 1 y 5.
     * @param rating Objeto Rating a guardar.
     * @return El Rating guardado.
     */
    @Override
    public Rating save(Rating rating) {
        if (rating.getRating() < 1 || rating.getRating() > 5) {
            throw new IllegalArgumentException("El rating debe estar entre 1 y 5");
        }
        return ratingRepository.save(rating);
    }

    /**
     * Elimina un Rating por su ID.
     * Maneja posibles excepciones.
     * @param id ID del Rating a eliminar.
     */
    @Override
    public void delete(Long id) {
        try {
            ratingRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el rating con ID " + id, e);
        }
    }

    /**
     * Verifica si un Rating existe por su ID.
     * @param id ID del Rating.
     * @return true si existe, false en caso contrario.
     */
    @Override
    public boolean exists(Long id) {
        return id != null && ratingRepository.existsById(id);
    }

    /**
     * Busca ratings por el nombre del producto.
     * @param product Nombre del producto.
     * @return Lista de Ratings asociados al producto.
     */
    public List<Rating> findByProduct(String product) {
        return ratingRepository.findByProduct(product);
    }
}
