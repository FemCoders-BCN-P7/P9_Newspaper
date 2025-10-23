package com.newspaper.newspaper.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400: cuerpo JSON inválido (mal formado, tipos incorrectos, etc.)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleNotReadable(HttpMessageNotReadableException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Malformed JSON or invalid value.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // 400: Bean Validation en @RequestBody (@NotBlank, @Email, @NotNull...)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
        .forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    // 400: Validaciones tipo @Size/@NotNull disparadas en parámetros/path/query (no body)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraint(ConstraintViolationException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }

    // 404: tus servicios lanzan IllegalArgumentException cuando no encuentran recursos
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    // 409: conflictos/duplicados en BD (p. ej., email único)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrity(DataIntegrityViolationException ex) {
        Map<String, String> body = new HashMap<>();
        // Mensaje amigable por defecto
        String msg = "Data integrity violation (possible duplicate or null where not allowed).";
        // Si detectamos "duplicate key" en PostgreSQL, lo explicitamos
        String root = ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : "";
        if (root != null && root.toLowerCase().contains("duplicate key")) {
            msg = "Email already exists.";
        }
        body.put("error", msg);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    // 500: cualquier otra cosa inesperada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneric(Exception ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Unexpected error. Please contact support.");
        // (Opcional) imprime en consola para depurar mientras desarrollas
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
