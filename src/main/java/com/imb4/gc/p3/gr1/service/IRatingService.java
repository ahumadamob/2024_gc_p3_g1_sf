package com.imb4.gc.p3.gr1.service;

import  com.imb4.gc.p3.gr1.entity.Rating;

import java.util.List;

public interface IRatingService {
    
    /**
     * Devuelve todos los ratings almacenados en la base de datos.
     * @return Lista de objetos Rating.
     */
    List<Rating> getAll();

    /**
     * Devuelve un Rating por su ID.
     * @param id ID del rating.
     * @return Objeto Rating o null si no se encuentra.
     */
    Rating getById(Long id);

    /**
     * Guarda o actualiza un Rating.
     * @param rating Objeto Rating a guardar.
     * @return El Rating guardado.
     */
    Rating save(Rating rating);

    /**
     * Elimina un Rating por su ID.
     * @param id ID del Rating a eliminar.
     */
    void delete(Long id);

    /**
     * Verifica si un Rating existe por su ID.
     * @param id ID del Rating.
     * @return true si existe, false en caso contrario.
     */
    boolean exists(Long id);
    List<Rating> getRatingsByProductId(Long productId);
    Double calculateAverageNoteByProductId(Long productId);
    List<Rating> findByProduct(String product);

	List<Rating> findByUser(String user);

	Rating approve(Long id);
}
