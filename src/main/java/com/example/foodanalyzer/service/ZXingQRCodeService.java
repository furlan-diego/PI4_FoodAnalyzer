package com.example.foodanalyzer.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Optional;

@Service
public class ZXingQRCodeService implements QRCodeService {

    @Override
    public Optional<String> decodeQRCode(BufferedImage bufferedImage) {
        if (bufferedImage == null) {
            return Optional.empty();
        }
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return Optional.of(result.getText());
        } catch (NotFoundException e) {
            // QR Code não encontrado na imagem
            return Optional.empty();
        }
    }
}
