package com.imb4.gc.p3.gr1.controller;

import com.imb4.gc.p3.gr1.entity.Rating;
import com.imb4.gc.p3.gr1.service.IRatingService;
import com.imb4.gc.p3.gr1.util.APIResponse;
import com.imb4.gc.p3.gr1.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/rating")
public class RatingController {

    @Autowired
    private IRatingService ratingService;

    // Obtener todos los ratings
    @GetMapping
    public ResponseEntity<APIResponse<List<Rating>>> getAllRating() {
        List<Rating> ratings = ratingService.getAll();
        return ratings.isEmpty() ? ResponseUtil.notFound("No se encontraron ratings") : ResponseUtil.success(ratings);
    }

    // Obtener rating por ID
    @GetMapping("{id}")
    public ResponseEntity<APIResponse<Rating>> getRatingById(@PathVariable("id") Long id) {
        return ratingService.exists(id) ? 
                ResponseUtil.success(ratingService.getById(id)) : 
                ResponseUtil.notFound("No se encontró el rating con el ID " + id);
    }

    // Crear un nuevo rating
    @PostMapping
    public ResponseEntity<APIResponse<Rating>> createRating(@RequestBody Rating rating) {
        if (rating.getId() != null && ratingService.exists(rating.getId())) {
            return ResponseUtil.badRequest("El rating con el ID " + rating.getId() + " ya existe");
        }
        Rating newRating = ratingService.save(rating);
        return ResponseUtil.success(newRating);
    }

    // Actualizar un rating existente
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Rating>> updateRating(@PathVariable Long id, @RequestBody Rating rating) {
        if (!ratingService.exists(id)) {
            return ResponseUtil.badRequest("No se encontró el rating con el ID " + id);
        }
        rating.setId(id); // Asegurarse de que el ID coincide con el de la URL
        Rating updatedRating = ratingService.save(rating);
        return ResponseUtil.success(updatedRating);
    }

    // Eliminar un rating por ID
    @DeleteMapping("{id}")
    public ResponseEntity<APIResponse<String>> deleteRating(@PathVariable("id") Long id) {
        if (ratingService.exists(id)) {
            ratingService.delete(id);
            return ResponseUtil.success("El rating con el ID " + id + " ha sido eliminado con éxito.");
        } else {
            return ResponseUtil.notFound("No se encontró el rating con el ID " + id);
        }
    }
}
