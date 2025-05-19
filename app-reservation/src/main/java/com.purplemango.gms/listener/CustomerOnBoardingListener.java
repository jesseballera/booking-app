package com.purplemango.gms.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.purplemango.gms.config.TopicLibrary;
import com.purplemango.gms.models.reservation.Customer;
import com.purplemango.gms.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class CustomerOnBoardingListener {
    final ObjectMapper objectMapper = new ObjectMapper();
//    private final CustomerRepository customerRepository;
//
//    @Autowired
//    public CustomerOnBoardingListener(@Qualifier("customerRepository") CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

    @KafkaListener(topics = TopicLibrary.CUSTOMER_ONBOARD, groupId = TopicLibrary.GMS_RESERVATION_GROUP_ID)
    public void processMessage(@Payload Map<String, Customer> payload) {
        log.info("Processing message: {} from topic {}", payload, TopicLibrary.CUSTOMER_ONBOARD);
//        Customer customer = objectMapper.convertValue(payload, Customer.class);
//        customerRepository.save(customer);
    }
}
