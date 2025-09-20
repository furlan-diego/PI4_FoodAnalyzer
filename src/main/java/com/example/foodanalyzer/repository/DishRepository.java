package com.example.foodanalyzer.repository;

import com.example.foodanalyzer.model.Dish;
import java.util.Optional;

public interface DishRepository {
    Optional<Dish> findById(String id);
}
