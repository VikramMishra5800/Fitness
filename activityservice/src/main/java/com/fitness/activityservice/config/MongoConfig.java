package com.fitness.activityservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoAuditing                     
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(
            new SimpleMongoClientDatabaseFactory(
                MongoClients.create(uri),
                database
            )
        );
    }
}
