package com.example.foodanalyzer.service;

import com.example.foodanalyzer.model.Dish;
import com.example.foodanalyzer.repository.DishRepository;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class FoodAnalysisServiceImpl implements FoodAnalysisService {

    private final QRCodeService qrCodeService;
    private final ImageRecognitionService imageRecognitionService;
    private final DishRepository dishRepository;

    public FoodAnalysisServiceImpl(QRCodeService qrCodeService, 
                                   ImageRecognitionService imageRecognitionService, 
                                   DishRepository dishRepository) {
        this.qrCodeService = qrCodeService;
        this.imageRecognitionService = imageRecognitionService;
        this.dishRepository = dishRepository;
    }

    @Override
    public Optional<Dish> analyzeImage(InputStream imageInputStream) {
        try {
            BufferedImage image = ImageIO.read(imageInputStream);
            if (image == null) {
                return Optional.empty();
            }

            // 1. Tenta decodificar o QR Code primeiro
            Optional<String> qrCodeId = qrCodeService.decodeQRCode(image);
            if (qrCodeId.isPresent()) {
                String decodedText = qrCodeId.get().trim();
                System.out.println("QR Code detectado. Buscando prato com qrCodeId: " + decodedText);
                return dishRepository.findByQrCodeId(decodedText);
            }

            // 2. Se não houver QR Code, usa o reconhecimento de imagem
            System.out.println("Nenhum QR Code encontrado. Tentando reconhecimento de imagem...");
            Optional<String> dishIdFromMl = imageRecognitionService.identifyDish(image);
            if (dishIdFromMl.isPresent()) {
                // O serviço mock retorna um qrCodeId
                return dishRepository.findByQrCodeId(dishIdFromMl.get());
            }

            return Optional.empty();

        } catch (IOException e) {
            // Logar o erro em um cenário real
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
