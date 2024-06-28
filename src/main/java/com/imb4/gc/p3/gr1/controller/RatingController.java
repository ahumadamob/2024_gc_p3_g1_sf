package com.imb4.gc.p3.gr1.controller;


import com.imb4.gc.p3.gr1.entity.Rating;
import com.imb4.gc.p3.gr1.service.IRatingService;
import com.imb4.gc.p3.gr1.util.APIResponse;
import com.imb4.gc.p3.gr1.util.ResponseUtil;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/rating")
public class RatingController {

    @Autowired
    private IRatingService ratingService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Rating>>> getAllRating(){
        List<Rating> rating = ratingService.getAll();
        return rating.isEmpty() ? ResponseUtil.notFound("No se encontraron ratingins") : ResponseUtil.success(rating);
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponse<Rating>> getRatingById(@PathVariable("id")Long id){
        return ratingService.exists(id) ? ResponseUtil.success(ratingService.getById(id)) : ResponseUtil.notFound("No se encontro el id ", id);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Rating>> createRating(@RequestBody Rating rating){
        return ratingService.exists(rating.getId()) ? ResponseUtil.badRequest("Ya existe el rating", rating.getId()) :
                ResponseUtil.success(ratingService.save(rating));
   }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Rating>> updateRating(@PathVariable Long id, @RequestBody Rating rating) {
        if (!ratingService.exists(id)) {
            return ResponseUtil.badRequest("No existe el rating", id);
        }
        rating.setId(id); 
        Rating updatedRating = ratingService.save(rating);
        return ResponseUtil.success(updatedRating);
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
}
