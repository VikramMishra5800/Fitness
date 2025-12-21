package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepo;

    public List<Recommendation> getUserRecommendations(String userId) {
        return recommendationRepo.findByUserId(userId);
    }

    public Recommendation getActivityRecommendations(String activityId) {
        return recommendationRepo.findByActivityId(activityId)
                .orElseThrow(()->new RuntimeException("Activity not found"));
    }
}
