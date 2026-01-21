package com.pgFinder.PgFinder.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pgFinder.PgFinder.entity.Pg;
import com.pgFinder.PgFinder.service.PgService;

//import com.pgfinder.entity.Pg;
//import com.pgfinder.service.PgService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;

@RestController
@RequestMapping("/pg")
public class PgController {

    private final PgService pgService;

    public PgController(PgService pgService) {
        this.pgService = pgService;
    }

    @GetMapping
    public List<Pg> getAllPgs() {
        return pgService.getAllPgs();
    }

    @PostMapping
    public ResponseEntity<?> addPg(@RequestParam String ownerName, @RequestParam String phoneNumber, @RequestParam MultipartFile photo) throws IOException {
        String photoPath = savePhoto(photo);
        Pg pg = new Pg(ownerName, phoneNumber, photoPath);
        pgService.savePg(pg);
        return ResponseEntity.ok("PG added successfully");
    }

    private String savePhoto(MultipartFile photo) throws IOException {
        Path uploadPath = Paths.get("uploads");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileName = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(photo.getInputStream(), filePath);
        return filePath.toString();
    }
}