package com.gokul.ai.ai_ecommerce.Controller;

import com.gokul.ai.ai_ecommerce.ProductImageUpload.ImageResizeService;
import com.gokul.ai.ai_ecommerce.Util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductImageController {

    @Autowired
    private ImageResizeService imageResizeService;

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<?> uploadProductImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile multipartFile) {

        try {
            String uploadDir = "uploads/products/" + id;
            String fileName = multipartFile.getOriginalFilename();

            String savedPath = FileUploadUtil.saveFile(uploadDir, fileName, multipartFile.getInputStream());

            // Resize to 500x500
            imageResizeService.resizeImage(savedPath, 500, 500);

            return ResponseEntity.ok("Image uploaded & resized successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Upload failed: " + e.getMessage());
        }
    }
}
