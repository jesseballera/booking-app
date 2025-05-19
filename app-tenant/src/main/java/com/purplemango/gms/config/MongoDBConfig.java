package com.purplemango.gms.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

//@EnableMongoRepositories
@Configuration
public class MongoDBConfig extends AbstractMongoClientConfiguration {

    private final String mongoURI;
    private final String mongoDatabase;
    private final String mongoAuthDB;

    @Autowired
    public MongoDBConfig(@Value("${spring.data.mongodb.uri}") String mongoURI,
                         @Value("${spring.data.mongodb.database}") String mongoDatabase,
                         @Value(("${spring.data.mongodb.dbAuthSource}")) String mongoAuthDB) {
        this.mongoURI = mongoURI;
        this.mongoDatabase = mongoDatabase;
        this.mongoAuthDB = mongoAuthDB;
    }

    @Bean
    @Override
    protected String getDatabaseName() {
        ThreadLocal<String> threadLocal = MultiTenantMongoDBFactory.dbName;
        String dbName = threadLocal.get();
        if (dbName == null || dbName.isEmpty()) {
            dbName = mongoDatabase;
        }
        return dbName;
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoURI);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

    @Bean(name = "mongoTemplateAuth")
    public MongoTemplate mongoTemplateAuth() {
        return new MongoTemplate(mongoClient(), mongoAuthDB);
    }

}
