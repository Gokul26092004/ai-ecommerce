package com.gokul.ai.ai_ecommerce.Controller;

import com.gokul.ai.ai_ecommerce.Service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RecommendationController {

    @Autowired
    private AIService aiService;

    @GetMapping("/recommend/{id}")
    public String recommend(@PathVariable int id){
        return aiService.getRecommendations(id);
    }
}
