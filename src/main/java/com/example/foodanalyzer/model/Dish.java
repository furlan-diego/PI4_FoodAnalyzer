package com.example.foodanalyzer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Chave primária para o banco de dados

    @Column(nullable = false, unique = true)
    private String qrCodeId; // ID de negócio, lido do QR Code

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double calories;

    // JPA requer um construtor sem argumentos
    public Dish() {
    }

    public Dish(String qrCodeId, String name, double calories) {
        this.qrCodeId = qrCodeId;
        this.name = name;
        this.calories = calories;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrCodeId() {
        return qrCodeId;
    }

    public void setQrCodeId(String qrCodeId) {
        this.qrCodeId = qrCodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}
