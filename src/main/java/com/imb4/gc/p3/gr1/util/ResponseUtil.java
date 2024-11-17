package com.imb4.gc.p3.gr1.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class ResponseUtil {

    private ResponseUtil() {
        // Constructor privado para evitar instanciación
    }

    public static <T> ResponseEntity<APIResponse<T>> success(T data) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.OK.value(), null, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static <T> ResponseEntity<APIResponse<T>> created(T data) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.CREATED.value(), null, data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public static <T> ResponseEntity<APIResponse<T>> successDeleted(String message, Long id) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.OK.value(), message(message.replace("{0}", id.toString())), null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static <T> ResponseEntity<APIResponse<T>> error(HttpStatus status, String message) {
        APIResponse<T> response = new APIResponse<>(status.value(), addSingleMessage(message), null);
        return ResponseEntity.status(status).body(response);
    }

    public static <T> ResponseEntity<APIResponse<T>> notFound(String message) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.NOT_FOUND.value(), message(message), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    public static <T> ResponseEntity<APIResponse<T>> notFound(String message, Long id) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.NOT_FOUND.value(), message(message.replace("{0}", id.toString())), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }    

    public static <T> ResponseEntity<APIResponse<T>> badRequest(String message) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), addSingleMessage(message), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    
    public static <T> ResponseEntity<APIResponse<T>> badRequest(String message, Long id) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), addSingleMessage(message.replace("{0}", id.toString())), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }    

    public static <T> ResponseEntity<APIResponse<T>> handleConstraintException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        APIResponse<T> response = new APIResponse<T>(HttpStatus.BAD_REQUEST.value(), errors, null);
        return ResponseEntity.badRequest().body(response);
    }

    private static List<String> addSingleMessage(String message) {
        List<String> messages = new ArrayList<>();
        messages.add(message);
        return messages;
    }
    private static List<String> message(String message){
        List<String> messages = new ArrayList<>();
        messages.add(message);
        return messages;

    }

	public static ResponseEntity<APIResponse<String>> error(String string) {
		return null;
	}
}