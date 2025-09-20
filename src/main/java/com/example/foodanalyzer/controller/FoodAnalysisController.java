package com.example.foodanalyzer.controller;

import com.example.foodanalyzer.model.Dish;
import com.example.foodanalyzer.service.FoodAnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/analysis")
public class FoodAnalysisController {

    private final FoodAnalysisService foodAnalysisService;

    public FoodAnalysisController(FoodAnalysisService foodAnalysisService) {
        this.foodAnalysisService = foodAnalysisService;
    }

    @PostMapping("/image")
    public ResponseEntity<Dish> analyzeImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Optional<Dish> dish = foodAnalysisService.analyzeImage(file.getInputStream());

            return dish.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());

        } catch (IOException e) {
            // Logar o erro em um cenário real
            e.printStackTrace();
            return ResponseEntity.status(500).build(); // Internal Server Error
        }
    }
}
