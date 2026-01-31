package com.gokul.ai.ai_ecommerce.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AIService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String getRecommendations(int productId){
        String url = "http://localhost:8000/recommend/" + productId;
        return restTemplate.getForObject(url,String.class);
    }
}
