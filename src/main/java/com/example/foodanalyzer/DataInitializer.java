package com.example.foodanalyzer;

import com.example.foodanalyzer.model.Dish;
import com.example.foodanalyzer.repository.DishRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DishRepository dishRepository;

    public DataInitializer(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verifica se o banco de dados já tem dados para não inserir duplicatas
        if (dishRepository.count() == 0) {
            System.out.println("Iniciando população do banco de dados com dados de exemplo...");

            Dish dish1 = new Dish("prato-001", "Feijoada Completa", 860);
            Dish dish2 = new Dish("prato-002", "Salada Caesar com Frango", 450);
            Dish dish3 = new Dish("prato-003", "Pizza Margherita (fatia)", 280);
            Dish dish4 = new Dish("prato-004", "Sushi (10 peças)", 350);

            dishRepository.saveAll(List.of(dish1, dish2, dish3, dish4));

            System.out.println("Banco de dados populado com sucesso!");
        } else {
            System.out.println("O banco de dados já contém dados. Nenhuma ação necessária.");
        }
    }
}
