package com.xiomara.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public ResponseEntity<?> allAccess() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Contenido público.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> userAccess() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Contenido para usuarios.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<?> moderatorAccess() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Tablero de moderador.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminAccess() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Tablero de administrador.");
        return ResponseEntity.ok(response);
    }
}