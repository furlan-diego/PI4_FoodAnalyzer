package com.example.foodanalyzer.repository;

import com.example.foodanalyzer.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    /**
     * O Spring Data JPA implementará este metodo automaticamente.
     * Ele buscará um prato na tabela 'dishes' pela coluna 'qr_code_id'.
     */
    Optional<Dish> findByQrCodeId(String qrCodeId);
}
