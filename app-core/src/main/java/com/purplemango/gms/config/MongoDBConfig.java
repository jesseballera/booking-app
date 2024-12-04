package com.purplemango.gms.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@EnableMongoRepositories
@Configuration
public class MongoDBConfig extends AbstractMongoClientConfiguration {

    private final String mongoURI;
    private final String mongoDatabase;

    @Autowired
    public MongoDBConfig( @Value("${spring.data.mongodb.uri}") String mongoURI,
                          @Value("${spring.data.mongodb.database}") String mongoDatabase) {
        this.mongoURI = mongoURI;
        this.mongoDatabase = mongoDatabase;
    }

    @Bean
    @Override
    protected String getDatabaseName() {
        return mongoDatabase;
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoURI);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }
}
