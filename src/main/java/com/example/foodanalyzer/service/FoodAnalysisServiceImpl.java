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
                System.out.println("DEBUG: Não foi possível ler a imagem (ImageIO.read retornou null).");
                return Optional.empty();
            }

            // 1. Tenta decodificar o QR Code primeiro
            Optional<String> dishIdFromQr = qrCodeService.decodeQRCode(image);
            if (dishIdFromQr.isPresent()) {
                // Adicionando logs detalhados para depuração
                String decodedText = dishIdFromQr.get().trim(); // Remove espaços em branco
                System.out.println("DEBUG: Texto bruto decodificado do QR Code: '" + dishIdFromQr.get() + "'");
                System.out.println("DEBUG: Texto após trim() para busca: '" + decodedText + "'");

                Optional<Dish> dish = dishRepository.findById(decodedText);

                if (dish.isPresent()) {
                    System.out.println("DEBUG: Prato encontrado no repositório!");
                } else {
                    System.out.println("DEBUG: Prato NÃO encontrado no repositório para o ID: '" + decodedText + "'");
                }
                return dish;
            }

            // 2. Se não houver QR Code, usa o reconhecimento de imagem
            System.out.println("Nenhum QR Code encontrado. Tentando reconhecimento de imagem...");
            Optional<String> dishIdFromMl = imageRecognitionService.identifyDish(image);
            if (dishIdFromMl.isPresent()) {
                return dishRepository.findById(dishIdFromMl.get());
            }

            return Optional.empty();

        } catch (IOException e) {
            // Logar o erro em um cenário real
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
