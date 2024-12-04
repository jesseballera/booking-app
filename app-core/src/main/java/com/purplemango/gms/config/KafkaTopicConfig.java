/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purplemango.gms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 *
 * @author Jesse Ballera
 */
@Configuration
public class KafkaTopicConfig {

//    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public NewTopic computePayout() {
        return TopicBuilder.name(TopicLibrary.CUSTOMER_ONBOARD).build();
    }

}
