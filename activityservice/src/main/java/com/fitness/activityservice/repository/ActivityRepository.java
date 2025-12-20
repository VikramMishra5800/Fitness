package com.fitness.activityservice.repository;

import com.fitness.activityservice.models.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity,String> {
}
