package com.gokul.ai.ai_ecommerce.ProductImageUpload;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

public class FileUploadUtil {

    public static String saveFile(String uploadDir, String fileName, InputStream fileStream) throws IOException {

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(fileStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }
}

