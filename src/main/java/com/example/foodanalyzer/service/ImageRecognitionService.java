package com.example.foodanalyzer.service;

import java.awt.image.BufferedImage;
import java.util.Optional;

public interface ImageRecognitionService {

    /**
     * Simula a identificação de um prato a partir de uma imagem usando um modelo de Machine Learning.
     * @param image A imagem do prato.
     * @return O ID do prato identificado, se a identificação for bem-sucedida.
     */
    Optional<String> identifyDish(BufferedImage image);
}
