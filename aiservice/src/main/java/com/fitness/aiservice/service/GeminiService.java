package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Activity;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final Client geminiClient;

    public String getRecommendations(String prompt) {
        try{
            GenerateContentResponse response =
                    geminiClient.models.generateContent(
                            "gemini-2.5-flash",
                            prompt,
                            null);

            return response.text();
        }catch(Exception ex){
            ex.printStackTrace();
            return StringUtils.EMPTY;
        }

    }
}
