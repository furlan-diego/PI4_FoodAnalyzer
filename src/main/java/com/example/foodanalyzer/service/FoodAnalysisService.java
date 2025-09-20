package com.example.foodanalyzer.service;

import com.example.foodanalyzer.model.Dish;

import java.io.InputStream;
import java.util.Optional;

public interface FoodAnalysisService {

    /**
     * Analisa uma imagem para extrair informações de um prato.
     * A análise tenta primeiro ler um QR Code. Se não encontrar, usa o reconhecimento de imagem.
     *
     * @param imageInputStream O fluxo de entrada da imagem a ser analisada.
     * @return Um Optional contendo o Prato, se encontrado.
     */
    Optional<Dish> analyzeImage(InputStream imageInputStream);
}
