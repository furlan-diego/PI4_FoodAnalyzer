package com.example.foodanalyzer.repository;

import com.example.foodanalyzer.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryDishRepository implements DishRepository {

    private static final Map<String, Dish> DISH_DATABASE = new HashMap<>();

    static {
        // Dados de exemplo
        DISH_DATABASE.put("prato-001", new Dish("prato-001", "Feijoada Completa", 860));
        DISH_DATABASE.put("prato-002", new Dish("prato-002", "Salada Caesar com Frango", 450));
        DISH_DATABASE.put("prato-003", new Dish("prato-003", "Pizza Margherita (fatia)", 280));
        DISH_DATABASE.put("prato-004", new Dish("prato-004", "Sushi (10 peças)", 350));
    }

    @Override
    public Optional<Dish> findById(String id) {
        return Optional.ofNullable(DISH_DATABASE.get(id));
    }
}
