package com.example.crud.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
// import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoDatabase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigMongoConnector {
  @Bean
  public MongoClient mongoClient(){
    return MongoClients.create();
  }

  @Bean
  public MongoDatabase mongoDatabase(){
    return mongoClient().getDatabase("traveloka");
  }
}
