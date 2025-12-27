package com.fitness.aiservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityMessageListener {

    private final ActivityAiService activityAiService;
    private final RecommendationRepository recommendationRepository;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "activity-processor-group")
    public void processActivity(Activity activity) throws JsonProcessingException {
        log.info("Received Activity: {}", activity.getUserId());
        Recommendation recommendation = activityAiService.generateRecommendation(activity);

        log.info("Saving Data to Recommendation table: {}", recommendation.getUserId());
        recommendationRepository.save(recommendation);
        log.info("Data saved successfully to Recommendation table: {}", recommendation.getId());
    }
}
