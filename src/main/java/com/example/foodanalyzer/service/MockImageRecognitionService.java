package com.example.foodanalyzer.service;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Optional;

@Service
public class MockImageRecognitionService implements ImageRecognitionService {

    /**
     * Simula o reconhecimento de imagem. Em um cenário real, aqui ocorreria a chamada
     * a um modelo de Machine Learning.
     *
     * @param image A imagem do prato (não utilizada nesta simulação).
     * @return Retorna sempre o ID "prato-002" para fins de demonstração.
     */
    @Override
    public Optional<String> identifyDish(BufferedImage image) {
        // Simulação: o modelo de ML "reconheceu" a imagem como uma Salada Caesar.
        // Em uma implementação real, você processaria a imagem e retornaria o ID correspondente.
        System.out.println("Simulando reconhecimento de imagem... Prato 'prato-002' identificado.");
        return Optional.of("prato-002");
    }
}
