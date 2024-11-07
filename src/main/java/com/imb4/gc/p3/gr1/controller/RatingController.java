package com.imb4.gc.p3.gr1.controller;

import com.imb4.gc.p3.gr1.entity.Rating;
import com.imb4.gc.p3.gr1.service.IRatingService;
import com.imb4.gc.p3.gr1.util.APIResponse;
import com.imb4.gc.p3.gr1.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/rating")
public class RatingController {

    @Autowired
    private IRatingService ratingService;

    // Obtener todos los ratings
    @GetMapping
    public ResponseEntity<APIResponse<List<Rating>>> getAllRating(){
        List<Rating> rating = ratingService.getAll();
        return rating.isEmpty() ? ResponseUtil.notFound("No se encontraron ratings") : ResponseUtil.success(rating);
    }

    // Obtener rating por ID
    @GetMapping("{id}")
    public ResponseEntity<APIResponse<Rating>> getRatingById(@PathVariable("id")Long id){
        return ratingService.exists(id) ? ResponseUtil.success(ratingService.getById(id)) : ResponseUtil.notFound("No se encontro el id ", id);
    }  

    // Obtener ratings por usuario
    @GetMapping("/user/{user}")
    public ResponseEntity<APIResponse<List<Rating>>> getRatingsByUser(@PathVariable("user") String user) {
        List<Rating> ratings = ratingService.findByUser(user);
        return ratings.isEmpty() ? ResponseUtil.notFound("No se encontraron ratings para el usuario " + user) : ResponseUtil.success(ratings);
    }

    // Obtener ratings por producto
    @GetMapping("/product/{product}")
    public ResponseEntity<APIResponse<List<Rating>>> getRatingsByProduct(@PathVariable("product") String product) {
        List<Rating> ratings = ratingService.findByProduct(product);
        return ratings.isEmpty() ? ResponseUtil.notFound("No se encontraron ratings para el producto " + product) : ResponseUtil.success(ratings);
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
    
    @PutMapping("{id}/approve")
    public ResponseEntity<APIResponse<Rating>> approveRating(@PathVariable("id") Long id) {
    	return ratingService.exists(id) ? ResponseUtil.success(ratingService.approve(id)) : ResponseUtil.badRequest("No existe el rating", id);
    }

   @DeleteMapping("{id}")
    public ResponseEntity<APIResponse<Rating>> deleteRating(@PathVariable("id") Long id){
       if (ratingService.exists(id)) {
           ratingService.delete(id);
           return ResponseUtil.badRequest("Eliminado el rating con el id: ", id);
       }else{
           return ResponseUtil.badRequest("No se encontro el rating con el id: ", id);
       }
   }
   
   @GetMapping("/product/{productId}")
	public ResponseEntity<Map<String, Object>> getRatingsByProductId(@PathVariable Long productId) {
	   
       List<Rating> ratings = ratingService.getRatingsByProductId(productId);
       Double average = ratingService.calculateAverageNoteByProductId(productId);
       Map<String, Object> response = new HashMap<>();
       response.put("ratings", ratings);
       response.put("average", average);

       return ResponseEntity.ok(response);
   }
}
