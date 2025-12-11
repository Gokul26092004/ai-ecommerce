package com.gokul.ai.ai_ecommerce.ProductImageUpload;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ImageResizeService {

    public void resizeImage(String imagePath, int width, int height) {
        try {
            String outputPath = imagePath.replace(".", "_small.");

            Thumbnails.of(new File(imagePath))
                    .size(width, height)
                    .toFile(new File(outputPath));  // creates resized version

        } catch (Exception e) {
            throw new RuntimeException("Error resizing image: " + e.getMessage());
        }
    }
}

