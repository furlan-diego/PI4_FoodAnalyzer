package com.example.foodanalyzer.service;

import java.awt.image.BufferedImage;
import java.util.Optional;

public interface QRCodeService {
    /**
     * Tenta decodificar um QR Code a partir de uma imagem.
     * @param image A imagem a ser processada.
     * @return O conteúdo do QR Code como uma String, se encontrado.
     */
    Optional<String> decodeQRCode(BufferedImage image);
}
